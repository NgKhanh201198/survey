package com.backend.survey.services;

public interface IAnswerService {
    void deleteAnswer(long id);

    boolean existsByAnswerId(long id);
}
