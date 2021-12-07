package com.backend.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultDTO {
    private List<QuestionDTO> questionDTOList;

    public ResultDTO() {
        super();
    }

    public ResultDTO(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public List<QuestionDTO> getQuestionDTOList() {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }
}
