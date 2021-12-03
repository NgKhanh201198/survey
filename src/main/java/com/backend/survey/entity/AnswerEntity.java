package com.backend.survey.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Data
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerId")
    private long answerId;

    @Column(name = "content")
    private String content;
    @Column(name = "status",length = 10)
    private String status;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity question;

    public AnswerEntity() {
        super();
    }

    public AnswerEntity(long answerId, String content, String status, QuestionEntity question) {
        this.answerId = answerId;
        this.content = content;
        this.status = status;
        this.question = question;
    }
}
