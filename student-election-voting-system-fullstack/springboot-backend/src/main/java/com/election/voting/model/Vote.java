package com.election.voting.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "votes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"electionId", "postId", "studentId"}, name = "uk_one_vote_per_post")
    }
)
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long electionId;
    private Long postId;
    private Long candidateId;
    private String studentId;
    private LocalDateTime timestamp;
    private String receiptCode;

    public Vote() {}
    public Vote(Long electionId, Long postId, Long candidateId, String studentId, String receiptCode) {
        this.electionId = electionId;
        this.postId = postId;
        this.candidateId = candidateId;
        this.studentId = studentId;
        this.timestamp = LocalDateTime.now();
        this.receiptCode = receiptCode;
    }
    public Long getId() { return id; }
    public Long getElectionId() { return electionId; }
    public Long getPostId() { return postId; }
    public Long getCandidateId() { return candidateId; }
    public String getStudentId() { return studentId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getReceiptCode() { return receiptCode; }
}