package com.backend.survey.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "result")
@Data
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultId")
    private long resultId;

    @Column(name = "numberOfCorrectAnswers")
    private int numberOfCorrectAnswers;
    @Column(name = "totalScore")
    private float totalScore;

    @OneToOne
    @JoinColumn(name = "userId", unique = true)
    private UserEntity user;

    public ResultEntity() {
        super();
    }

    public ResultEntity(long resultId, int numberOfCorrectAnswers, int totalScore, UserEntity user) {
        this.resultId = resultId;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
        this.totalScore = totalScore;
        this.user = user;
    }
}
