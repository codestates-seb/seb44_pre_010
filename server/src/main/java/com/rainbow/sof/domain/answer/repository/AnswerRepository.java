package com.rainbow.sof.domain.answer.repository;

import com.rainbow.sof.domain.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
<<<<<<< HEAD
=======

    Long countByQuestionQuestionId(Long questionId);
    Answer findByIdAndQuestionId(long answerId, long questionId);

>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
