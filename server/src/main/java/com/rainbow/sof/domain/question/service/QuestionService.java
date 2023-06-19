package com.rainbow.sof.domain.question.service;

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
public class QuestionService {
    final private QuestionRepository questionRepository;

    public Question createQuestion(Question request) {
        // TODO: USER 있는지 확인 로직
        return questionRepository.save(request);
    }

    public Question findQuestion(long id) {
        Question question = findVerifiedQuestion(id);
        increaseView(question);
        return question;
    }

    @Transactional(readOnly = true)
    public Question findVerifiedQuestion(long id){
        return questionRepository.findById(id).orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public Question updateQuestion(long id, Question request) {
        Question findQuestion = findVerifiedQuestion(id);

        Optional.ofNullable(request.getContent())
                .ifPresent(findQuestion::updateContent);
        Optional.ofNullable(request.getTitle())
                .ifPresent(findQuestion::updateTitle);

        return findVerifiedQuestion(id);
    }

    public void increaseView(Question question){
        question.updateView();
    }

    public void deleteQuestion(long id) {
        Question findquestion = findVerifiedQuestion(id);

        questionRepository.delete(findquestion);
    }
}
