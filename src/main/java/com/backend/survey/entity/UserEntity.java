package com.backend.survey.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private long userId;

    @Column(name = "username",length = 20)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role",length = 10)
    private String role;

    public UserEntity() {
        super();
    }

    public UserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
