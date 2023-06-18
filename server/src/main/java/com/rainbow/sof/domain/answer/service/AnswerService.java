package com.rainbow.sof.domain.answer.service;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answerPostDtoToAnswer) {
        //TODO: Users, question 이 있는지 확인하는 로직 추가

        return answerRepository.save(answerPostDtoToAnswer);
    }


}
