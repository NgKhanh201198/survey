package com.backend.survey.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private long answerId;
    private String content;
    private String status;

    public AnswerDTO() {
        super();
    }

    public AnswerDTO(long answerId, String content, String status) {
        this.answerId = answerId;
        this.content = content;
        this.status = status;
    }
}
