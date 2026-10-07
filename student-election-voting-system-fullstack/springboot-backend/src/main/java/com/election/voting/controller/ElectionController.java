package com.election.voting.controller;

import com.election.voting.model.*;
import com.election.voting.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/elections")
@CrossOrigin(origins = "*")
public class ElectionController {
    @Autowired private ElectionRepository electionRepository;
    @Autowired private PostRepository postRepository;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrent() {
        List<Election> all = electionRepository.findAll();
        return all.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(all.get(all.size() - 1));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Election election) {
        return ResponseEntity.ok(electionRepository.save(election));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable Long id) {
        return ResponseEntity.ok(postRepository.findByElectionIdOrderByDisplayOrderAsc(id));
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<?> addPost(@PathVariable Long id, @RequestBody Post post) {
        post.setElectionId(id);
        return ResponseEntity.ok(postRepository.save(post));
    }
}