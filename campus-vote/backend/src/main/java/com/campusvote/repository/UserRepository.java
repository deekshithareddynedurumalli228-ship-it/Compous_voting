package com.campusvote.repository;
import com.campusvote.model.User; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<User,Long>{ Optional<User> findByStudentId(String studentId);  }
