package com.campusvote.repository;
import com.campusvote.model.Candidate; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface CandidateRepository extends JpaRepository<Candidate,Long>{   }
