package com.rainbow.sof.domain.answer.repository;

import com.rainbow.sof.domain.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Long countByQuestionQuestionId(Long questionId);
    Answer findByIdAndQuestionId(long answerId, long questionId);

}
