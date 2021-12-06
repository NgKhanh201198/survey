package com.backend.survey.repository;

import com.backend.survey.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<AnswerEntity, Long> {
    boolean existsByAnswerId(long id);
}
