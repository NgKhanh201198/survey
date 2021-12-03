package com.backend.survey.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "question")
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private long questionId;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "question")
    private Collection<AnswerEntity> listAnswer;

    public QuestionEntity() {
        super();
    }

    public QuestionEntity(long questionId, String question, Collection<AnswerEntity> listAnswer) {
        this.questionId = questionId;
        this.question = question;
        this.listAnswer = listAnswer;
    }
}
