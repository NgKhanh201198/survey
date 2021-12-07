package com.backend.survey.services.impl;

import com.backend.survey.dto.AnswerDTO;
import com.backend.survey.dto.QuestionDTO;
import com.backend.survey.dto.ResultDTO;
import com.backend.survey.entity.AnswerEntity;
import com.backend.survey.entity.QuestionEntity;
import com.backend.survey.entity.ResultEntity;
import com.backend.survey.entity.UserEntity;
import com.backend.survey.repository.IQuestionRepository;
import com.backend.survey.repository.IResultRepository;
import com.backend.survey.repository.IUserRepository;
import com.backend.survey.response.ResultResponse;
import com.backend.survey.services.IResultService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements IResultService {
    private final IResultRepository resultRepository;
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;

    public ResultServiceImpl(IResultRepository resultRepository, IQuestionRepository questionRepository, IUserRepository userRepository) {
        this.resultRepository = resultRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResultResponse submit(ResultDTO resultDTO) {
        List<QuestionEntity> questionEntityList = questionRepository.findAll();
        int count = 0;
        for (QuestionEntity qe : questionEntityList) {
            boolean flag = true;
            for (QuestionDTO qd : resultDTO.getQuestionDTOList()) {
                if (qe.getQuestionId() == qd.getQuestionId()) {
                    for (AnswerDTO ad : qd.getListAnswer()) {
                        for (AnswerEntity ae : qe.getListAnswer()) {
                            if (ad.getAnswerId() == ae.getAnswerId()) {
                                if (!(ad.getStatus().equals(ae.getStatus()))) {
                                    flag = false;
                                }
                            }
                        }
                    }
                    if (flag == true) {
                        count++;
                    }
                }
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản này!"));

        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setTotalScore(count * 10 / questionEntityList.size());
        resultEntity.setNumberOfCorrectAnswers(count);
        resultEntity.setUser(userEntity);

        resultRepository.save(resultEntity);

        ResultResponse resultResponse = new ResultResponse(count * 10 / questionEntityList.size(), count * 1);
        return resultResponse;
    }
}
