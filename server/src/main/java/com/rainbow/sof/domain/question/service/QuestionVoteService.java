package com.rainbow.sof.domain.question.service;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.domain.QuestionVote;
import com.rainbow.sof.domain.question.repository.QuestionVoteRepository;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionVoteService {
    private final QuestionService questionService;
    private final UserService userService;
    private final QuestionVoteRepository questionVoteRepository;
    public int createQuestionVote(String email, long id, String voteStatus) {
        Question findQuestion = questionService.findVerifiedQuestion(id);
        User findUser = userService.findByUserFromEmail(email);
        checkUserVoteStatusForQuestion(findQuestion, findUser);

        QuestionVote questionVote = QuestionVote.createSelf(findQuestion, findUser, voteStatus);
        questionVoteRepository.save(questionVote);
        findQuestion.calculateVote(voteStatus);
        return findQuestion.getVote();
    }

    public void checkUserVoteStatusForQuestion(Question question, User user){
        for (QuestionVote questionVote : user.getQuestionVotes()) {
            if (Objects.equals(questionVote.getQuestion().getQuestionId(), question.getQuestionId()))
                throw new BusinessLogicException(ExceptionCode.QUESTION_VOTE_ALREADY);
        }
    }

}