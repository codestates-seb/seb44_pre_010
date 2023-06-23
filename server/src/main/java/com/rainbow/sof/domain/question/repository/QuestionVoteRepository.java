package com.rainbow.sof.domain.question.repository;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.domain.QuestionVote;
import com.rainbow.sof.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    Optional<QuestionVote> findByQuestionQuestionIdAndUserUserId(long question_questionId, long user_userId);
}
