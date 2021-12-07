package com.backend.survey.services.impl;

import com.backend.survey.repository.IAnswerRepository;
import com.backend.survey.services.IAnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements IAnswerService {
    private final IAnswerRepository answerRepository;

    public AnswerServiceImpl(IAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void deleteAnswer(long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public boolean existsByAnswerId(long id) {
        return answerRepository.existsByAnswerId(id);
    }
}
