package com.backend.survey.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse() {
        super();
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
