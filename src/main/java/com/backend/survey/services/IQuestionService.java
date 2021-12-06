package com.backend.survey.services;

import com.backend.survey.dto.QuestionDTO;

import java.util.List;

public interface IQuestionService {
    void addQuestion(QuestionDTO questionDTO);

    void updateQuestion(long id, QuestionDTO questionDTO);

    QuestionDTO getByQuestionId(long id);

    List<QuestionDTO> getAllQuestion();

    boolean existsByQuestionId(long id);
}
