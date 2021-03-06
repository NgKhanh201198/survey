package com.backend.survey.response;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {
    private Date timestamp;
    private int status;
    private String error;
    private String message;

    public MessageResponse() {
        super();
    }

    public MessageResponse(Date timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
