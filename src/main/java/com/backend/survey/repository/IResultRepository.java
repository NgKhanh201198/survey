package com.backend.survey.repository;

import com.backend.survey.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResultRepository extends JpaRepository<ResultEntity, Long> {
}
