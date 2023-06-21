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

<<<<<<< HEAD
<<<<<<< HEAD
    public Answer createAnswer(Answer answerPostDtoToAnswer) {
        //TODO: Users, question 이 있는지 확인하는 로직 추가

        return answerRepository.save(answerPostDtoToAnswer);
    }

    public Answer updateAnswer(long answerId, Answer request) {

        Answer findAnswer = findVerifiedAnswer(answerId);
=======
    public Answer createAnswer(long  questionId, Answer answer) {
        //TODO: User 가 있는지 확인하는 로직 추가
        findVerifiedQuestion(questionId);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(long questionId, long answerId, Answer request) {
        Answer findAnswer = findVerifiedAnswer(answerId, questionId);
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
=======
    public Answer createAnswer(long  questionId, Answer request, String email) {
        User findUser = userService.findByUserFromEmail(email);
        Question findQuestion = questionService.findVerifiedQuestion(questionId);
        request.insertUser(findUser);
        request.insertQuestion(findQuestion);
        return answerRepository.save(request);
    }

    public Answer updateAnswer(long questionId, long answerId, Answer request, String email) {
        questionService.findVerifiedQuestion(questionId);
        Answer findAnswer = findVerifiedAnswer(answerId);
        userService.findByUserFromEmail(email).checkIsMyself(findAnswer.getUser().getUserId());
>>>>>>> 4bf0b47384ae1e81260a33ae4f7dae3460a75e2f

        Optional.ofNullable(request.getContent())
                .ifPresent(findAnswer::updateContent);

<<<<<<< HEAD
<<<<<<< HEAD
        return findVerifiedAnswer(answerId);
    }

    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
=======
        return findVerifiedAnswer(answerId, questionId);
=======
        return findVerifiedAnswer(answerId);
>>>>>>> 4bf0b47384ae1e81260a33ae4f7dae3460a75e2f
    }

    public void deleteAnswer(long questionId, long answerId, String email) {
        questionService.findVerifiedQuestion(questionId);
        Answer findAnswer = findVerifiedAnswer(answerId);
        userService.findByUserFromEmail(email).checkIsMyself(findAnswer.getUser().getUserId());

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
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
    }
}
