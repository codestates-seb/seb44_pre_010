package com.rainbow.sof.domain.answer.controller;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.answer.mapper.AnswerMapper;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.global.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
@RestController
public class AnswerController {
    private final static String ANSWER_DEFAULT_URL = "/api/v1/questions";
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @PostMapping("/{question-id}/answers")
    public ResponseEntity saveAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerDto.Post request) {
        request.addQuestionId(questionId);
        Answer answer = answerService.createAnswer(answerMapper.answerDtoPostToAnswer(request));
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }
}
