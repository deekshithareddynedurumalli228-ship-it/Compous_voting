package com.election.voting.controller;

import com.election.voting.model.User;
import com.election.voting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String role = payload.get("role");
        if (username == null || username.trim().isEmpty()) return ResponseEntity.badRequest().body("Username required");

        Optional<User> u = userRepository.findByUsername(username.trim());
        if (u.isEmpty()) u = userRepository.findByStudentId(username.trim());
        if (u.isPresent()) return ResponseEntity.ok(u.get());

        if ("STUDENT".equalsIgnoreCase(role)) {
            User newUser = new User(username, "pass123", "Student " + username, "STUDENT", username, "HOSTEL", "Engineering", "2nd Year");
            return ResponseEntity.ok(userRepository.save(newUser));
        }
        return ResponseEntity.status(401).body(Map.of("error", "User not found"));
    }
}