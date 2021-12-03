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
    @Column(name = "password",length = 20)
    private String password;
    @Column(name = "role",length = 10)
    private String role;

//    @OneToOne(mappedBy = "user")
//    private ResultEntity result;

    public UserEntity() {
        super();
    }

    public UserEntity(long userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
