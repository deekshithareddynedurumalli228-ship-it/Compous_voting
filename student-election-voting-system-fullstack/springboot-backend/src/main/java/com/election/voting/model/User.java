package com.election.voting.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String fullName;
    private String role; // ADMIN or STUDENT
    private String studentId;
    private String residencyType; // HOSTEL or DAY_SCHOLAR
    private String department;
    private String year;

    public User() {}
    public User(String username, String password, String fullName, String role, String studentId, String residencyType, String department, String year) {
        this.username = username; this.password = password; this.fullName = fullName;
        this.role = role; this.studentId = studentId; this.residencyType = residencyType;
        this.department = department; this.year = year;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getResidencyType() { return residencyType; }
    public void setResidencyType(String residencyType) { this.residencyType = residencyType; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}