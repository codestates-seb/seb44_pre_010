package com.rainbow.sof.domain.answer.repository;

import com.rainbow.sof.domain.answer.domain.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    Optional<AnswerVote> findByAnswerAnswerIdAndUserUserId (Long answer_answerId, long user_userId);
}
