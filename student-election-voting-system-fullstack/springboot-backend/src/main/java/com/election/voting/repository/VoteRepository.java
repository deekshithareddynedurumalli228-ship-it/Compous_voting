package com.election.voting.repository;

import com.election.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByElectionIdAndPostIdAndStudentId(Long electionId, Long postId, String studentId);
    long countByCandidateId(Long candidateId);
    long countByPostId(Long postId);
    List<Vote> findByElectionId(Long electionId);
    List<Vote> findByStudentId(String studentId);
}