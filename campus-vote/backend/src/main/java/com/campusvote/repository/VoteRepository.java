package com.campusvote.repository;
import com.campusvote.model.Vote; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface VoteRepository extends JpaRepository<Vote,Long>{  boolean existsByStudentIdAndPostId(Long studentId,Long postId); long countByCandidateId(Long candidateId); List<Vote> findByStudentId(Long studentId); }
