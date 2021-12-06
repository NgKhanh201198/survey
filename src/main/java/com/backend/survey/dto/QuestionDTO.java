package com.backend.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private long questionId;
    private String question;
    private List<AnswerDTO> listAnswer;

    public QuestionDTO() {
        super();
    }

    public QuestionDTO(long questionId, String question, List<AnswerDTO> listAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.listAnswer = listAnswer;
    }
}
