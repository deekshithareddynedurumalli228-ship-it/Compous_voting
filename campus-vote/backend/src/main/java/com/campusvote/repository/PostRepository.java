package com.campusvote.repository;
import com.campusvote.model.Post; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PostRepository extends JpaRepository<Post,Long>{   }
