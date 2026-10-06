package com.campusvote.repository;
import com.campusvote.model.Election; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ElectionRepository extends JpaRepository<Election,Long>{   }
