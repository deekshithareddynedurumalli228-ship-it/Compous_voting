package com.election.voting.repository;

import com.election.voting.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByElectionIdOrderByDisplayOrderAsc(Long electionId);
}