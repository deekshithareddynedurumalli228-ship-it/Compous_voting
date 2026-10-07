package com.election.voting.repository;

import com.election.voting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByStudentId(String studentId);
    List<User> findByRole(String role);
}