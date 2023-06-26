package com.rainbow.sof.domain.answer.service;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.domain.AnswerVote;
import com.rainbow.sof.domain.answer.repository.AnswerVoteRepository;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@RequiredArgsConstructor
@Transactional
@Service
public class AnswerVoteService {
    private final AnswerService answerService;
    private final UserService userService;
    private final AnswerVoteRepository answerVoteRepository;

    public int createAnswerVote (long answerId, String voteStatus, String email) {
        Answer findAnswer = answerService.findVerifiedAnswer(answerId);
        User findUser = userService.findByUserFromEmail(email);
        checkUserVoteStatusForAnswer(findAnswer, findUser);

        AnswerVote answerVote = AnswerVote.createSelf(findAnswer, findUser, voteStatus);
        answerVoteRepository.save(answerVote);
        findAnswer.calculatePostVote(answerVote.getAnswerVoteStatus());
        return findAnswer.getVote();
    }

    public int deleteAnswerVote (long answerId, String email) {
        Answer findAnswer = answerService.findVerifiedAnswer(answerId);
        User findUser = userService.findByUserFromEmail(email);
        AnswerVote findVote = answerVoteRepository.findByAnswerAnswerIdAndUserUserId(answerId,
                findUser.getUserId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_VOTE_NOT_FOUND));
        findAnswer.calculateDeleteVote(findVote.getAnswerVoteStatus());
        answerVoteRepository.delete(findVote);
        return findAnswer.getVote();
    }

    public void checkUserVoteStatusForAnswer (Answer answer, User user) {
        for (AnswerVote answerVote : user.getAnswerVotes()) {
            if (Objects.equals(answerVote.getAnswer().getAnswerId(), answer.getAnswerId()))
                throw new BusinessLogicException(ExceptionCode.ANSWER_VOTE_ALREADY);
        }
    }
}
