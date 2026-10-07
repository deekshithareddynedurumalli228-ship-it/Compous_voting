package com.election.voting.controller;

import com.election.voting.model.Candidate;
import com.election.voting.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {
    @Autowired private CandidateRepository candidateRepository;

    @GetMapping("/election/{electionId}")
    public ResponseEntity<List<Candidate>> getByElection(@PathVariable Long electionId) {
        return ResponseEntity.ok(candidateRepository.findByElectionId(electionId));
    }

    @PostMapping
    public ResponseEntity<?> addCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(candidateRepository.save(candidate));
    }
}