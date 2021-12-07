package com.backend.survey.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    private String role;

    public UserResponse() {
        super();
    }

    public UserResponse(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
