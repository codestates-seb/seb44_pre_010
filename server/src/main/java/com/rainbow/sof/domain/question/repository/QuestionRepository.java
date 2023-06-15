package com.rainbow.sof.domain.question.repository;

import com.rainbow.sof.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
