package com.rainbow.sof.domain.answer.service;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.repository.AnswerRepository;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
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
    private final QuestionRepository questionRepository;

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

        Optional.ofNullable(request.getContent())
                .ifPresent(findAnswer::updateContent);

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
    }

    public void deleteAnswer(long questionId, long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId, questionId);

        answerRepository.delete(findAnswer);
    }

    public Question findVerifiedQuestion(long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public Answer findAnswer(long answerId, long questionId) {
        return findVerifiedAnswer(answerId, questionId);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId, long questionId) {
        return answerRepository.findByIdAndQuestionId(answerId, questionId);
    }

    public long getAnswerCnt(long id){
        return answerRepository.countByQuestionQuestionId(id);
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
    }
}
