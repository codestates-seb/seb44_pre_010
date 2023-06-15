package com.rainbow.sof.domain.question.service;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
import com.rainbow.sof.global.error.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class QuestionService {
    final private QuestionRepository questionRepository;

    public Question createQuestion(Question questionDtoPostToQuestion) {
        // TODO: USER 있는지 확인 로직
        return questionRepository.save(questionDtoPostToQuestion);
    }

}
