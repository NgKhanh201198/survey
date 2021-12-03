package com.backend.survey.response;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {
    private Date timestamp;
    private int statusCode;
    private String error;
    private String message;

    public MessageResponse() {
        super();
    }

    public MessageResponse(Date timestamp, int statusCode, String error, String message) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
    }
}
