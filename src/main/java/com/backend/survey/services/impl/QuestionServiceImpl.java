package com.backend.survey.services.impl;

import com.backend.survey.dto.AnswerDTO;
import com.backend.survey.dto.QuestionDTO;
import com.backend.survey.entity.AnswerEntity;
import com.backend.survey.entity.QuestionEntity;
import com.backend.survey.repository.IAnswerRepository;
import com.backend.survey.repository.IQuestionRepository;
import com.backend.survey.services.IQuestionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements IQuestionService {
    private final IQuestionRepository questionRepository;
    private final IAnswerRepository answerRepository;

    public QuestionServiceImpl(IQuestionRepository questionRepository, IAnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    @Transactional
    public void addQuestion(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(questionDTO.getQuestion());
        QuestionEntity questionEntityInsert = questionRepository.save(questionEntity);

        questionDTO.getListAnswer().forEach(answerDTO -> {
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setContent(answerDTO.getContent());
            answerEntity.setStatus(answerDTO.getStatus());
            answerEntity.setQuestion(questionEntityInsert);
            answerRepository.save(answerEntity);
        });
    }

    @Override
    public void updateQuestion(long id, QuestionDTO questionDTO) {
        QuestionEntity questionEntity = questionRepository.getById(id);
        questionEntity.setQuestion(questionDTO.getQuestion());
        QuestionEntity questionEntityInsert = questionRepository.save(questionEntity);

        questionDTO.getListAnswer().forEach(answerDTO -> {
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setAnswerId(answerDTO.getAnswerId());
            answerEntity.setContent(answerDTO.getContent());
            answerEntity.setStatus(answerDTO.getStatus());
            answerEntity.setQuestion(questionEntityInsert);
            answerRepository.save(answerEntity);
        });
    }

    @Override
    public QuestionDTO getByQuestionId(long id) {
        QuestionEntity questionEntity = questionRepository.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(questionEntity.getQuestionId());
        questionDTO.setQuestion(questionEntity.getQuestion());

        List<AnswerDTO> answerDTOList = questionEntity.getListAnswer().stream().map(answerEntity -> {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setAnswerId(answerEntity.getAnswerId());
            answerDTO.setContent(answerEntity.getContent());
            answerDTO.setStatus(answerEntity.getStatus());
            return answerDTO;
        }).collect(Collectors.toList());

        questionDTO.setListAnswer(answerDTOList);
        return questionDTO;
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        List<QuestionEntity> questionEntityList = questionRepository.findAll(Sort.by(Sort.Direction.ASC, "questionId"));
        List<QuestionDTO> questionDTOList = questionEntityList.stream().map(questionEntity -> {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(questionEntity.getQuestionId());
            questionDTO.setQuestion(questionEntity.getQuestion());

            List<AnswerDTO> answerDTOList = questionEntity.getListAnswer().stream().map(answerEntity -> {
                AnswerDTO answerDTO = new AnswerDTO();
                answerDTO.setAnswerId(answerEntity.getAnswerId());
                answerDTO.setContent(answerEntity.getContent());
                answerDTO.setStatus(answerEntity.getStatus());
                return answerDTO;
            }).collect(Collectors.toList());

            questionDTO.setListAnswer(answerDTOList);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOList;
    }

    @Override
    public boolean existsByQuestionId(long id) {
        return questionRepository.existsByQuestionId(id);
    }
}
