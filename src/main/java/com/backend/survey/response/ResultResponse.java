package com.backend.survey.response;

import lombok.Data;

@Data
public class ResultResponse {
    private float totalScore;
    private int numberOfCorrectAnswers;

    public ResultResponse() {
        super();
    }

    public ResultResponse(float totalScore, int numberOfCorrectAnswers) {
        this.totalScore = totalScore;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }
}
