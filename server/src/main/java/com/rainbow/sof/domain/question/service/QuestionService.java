package com.rainbow.sof.domain.question.service;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionService {
    private final int SIZE = 10;
    private final QuestionRepository questionRepository;
    private final UserService userService;

    public Question createQuestion(Question request, String email) {
        User user = userService.findByUserFromEmail(email);
        request.insertUser(user);
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

    @Transactional(readOnly = true)
    public List<Question> findQuestions() {
        return questionRepository.findTop20ByOrderByViewDesc();
    }

    @Transactional(readOnly = true)
    public Page<Question> findPageQuestions(String sort, int Page) {
        if(sort.equals("Newest"))
            return questionRepository.findAll(PageRequest.of(Page - 1, SIZE, Sort.by("questionId").descending()));
        else
            return questionRepository.findAll(PageRequest.of(Page - 1, SIZE, Sort.by("questionId").ascending()));
    }
}
