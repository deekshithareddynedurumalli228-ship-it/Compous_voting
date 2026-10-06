package com.campusvote.model;
import jakarta.persistence.*;
@Entity @Table(name="users") public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
 @Column(unique=true,nullable=false) String studentId; @Column(nullable=false) String name; @Column(unique=true,nullable=false) String email; @Column(nullable=false) String password; @Enumerated(EnumType.STRING) Role role; String department; boolean active=true;
 public enum Role{ADMIN,STUDENT} public User(){} public User(String sid,String n,String e,String p,Role r,String d){studentId=sid;name=n;email=e;password=p;role=r;department=d;}
 public Long getId(){return id;} public String getStudentId(){return studentId;} public String getName(){return name;} public String getEmail(){return email;} public String getPassword(){return password;} public Role getRole(){return role;} public String getDepartment(){return department;} public boolean isActive(){return active;}
}
