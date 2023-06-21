package com.rainbow.sof.domain.answer.service;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.repository.AnswerRepository;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.service.QuestionService;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final UserService userService;

    public Answer createAnswer(long  questionId, Answer answer, String email) {
        User findUser = userService.findByUserFromEmail(email);
        Question findQuestion = questionService.findVerifiedQuestion(questionId);
        answer.insertUser(findUser);
        answer.insertQuestion(findQuestion);
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(long questionId, long answerId, Answer request) {
        questionService.findVerifiedQuestion(questionId);
        Answer findAnswer = findVerifiedAnswer(answerId);

        Optional.ofNullable(request.getContent())
                .ifPresent(findAnswer::updateContent);

        return findVerifiedAnswer(answerId);
    }

    public void deleteAnswer(long questionId, long answerId) {
        questionService.findVerifiedQuestion(questionId);
        Answer findAnswer = findVerifiedAnswer(answerId);

        answerRepository.delete(findAnswer);
    }


    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    public long getAnswerCnt(long id){
        return answerRepository.countByQuestionQuestionId(id);
    }
}
