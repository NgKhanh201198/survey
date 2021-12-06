package com.backend.survey.repository;

import com.backend.survey.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    boolean existsByQuestionId(long id);
}
