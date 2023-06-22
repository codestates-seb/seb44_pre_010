package com.rainbow.sof.domain.question.repository;

import com.rainbow.sof.domain.question.domain.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
