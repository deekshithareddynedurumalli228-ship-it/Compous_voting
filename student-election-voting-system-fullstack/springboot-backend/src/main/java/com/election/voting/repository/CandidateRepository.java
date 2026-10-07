package com.election.voting.repository;

import com.election.voting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByElectionId(Long electionId);
    List<Candidate> findByPostId(Long postId);
}