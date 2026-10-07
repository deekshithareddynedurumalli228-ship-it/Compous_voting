package com.election.voting.controller;

import com.election.voting.model.*;
import com.election.voting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired private VoteRepository voteRepository;
    @Autowired private ElectionRepository electionRepository;
    @Autowired private PostRepository postRepository;
    @Autowired private CandidateRepository candidateRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping("/cast")
    public ResponseEntity<?> castVote(@RequestBody Map<String, Object> payload) {
        Long electionId = Long.valueOf(payload.get("electionId").toString());
        Long postId = Long.valueOf(payload.get("postId").toString());
        Long candidateId = Long.valueOf(payload.get("candidateId").toString());
        String studentId = payload.get("studentId").toString();

        Optional<Election> opt = electionRepository.findById(electionId);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "Election not found"));

        Election election = opt.get();
        LocalDateTime now = LocalDateTime.now();
        if (election.getStartTime() != null && now.isBefore(election.getStartTime())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Voting has not commenced yet."));
        }
        if (election.getEndTime() != null && now.isAfter(election.getEndTime())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Voting is closed."));
        }

        // 1 vote per post enforcement
        if (voteRepository.existsByElectionIdAndPostIdAndStudentId(electionId, postId, studentId)) {
            return ResponseEntity.badRequest().body(Map.of("error", "You have already cast a vote for this post!"));
        }

        String receiptCode = "REC-" + (1000 + new Random().nextInt(9000));
        Vote vote = new Vote(electionId, postId, candidateId, studentId, receiptCode);
        Vote saved = voteRepository.save(vote);
        return ResponseEntity.ok(Map.of("success", true, "receiptCode", saved.getReceiptCode()));
    }

    @GetMapping("/election/{electionId}/results")
    public ResponseEntity<?> getResults(@PathVariable Long electionId) {
        List<Post> posts = postRepository.findByElectionIdOrderByDisplayOrderAsc(electionId);
        List<Candidate> candidates = candidateRepository.findByElectionId(electionId);
        List<Vote> votes = voteRepository.findByElectionId(electionId);

        List<Map<String, Object>> postResults = new ArrayList<>();
        for (Post post : posts) {
            List<Map<String, Object>> candResults = new ArrayList<>();
            long totalPostVotes = voteRepository.countByPostId(post.getId());
            long maxVotes = -1;
            Candidate winner = null;

            for (Candidate c : candidates) {
                if (c.getPostId().equals(post.getId())) {
                    long count = voteRepository.countByCandidateId(c.getId());
                    if (count > maxVotes) { maxVotes = count; winner = c; }
                    double pct = totalPostVotes > 0 ? ((double) count / totalPostVotes) * 100 : 0;
                    candResults.add(Map.of("candidate", c, "votes", count, "percentage", Math.round(pct)));
                }
            }
            candResults.sort((a, b) -> Long.compare((Long) b.get("votes"), (Long) a.get("votes")));
            postResults.add(Map.of("post", post, "totalVotes", totalPostVotes, "candidateResults", candResults, "winner", totalPostVotes > 0 ? winner : null));
        }
        return ResponseEntity.ok(Map.of("electionId", electionId, "postResults", postResults));
    }
}