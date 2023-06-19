package com.rainbow.sof.domain.answer.controller;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.answer.mapper.AnswerMapper;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.global.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
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
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerDto.Post request) {
<<<<<<< HEAD
        request.addQuestionId(questionId);
        Answer answer = answerService.createAnswer(answerMapper.answerDtoPostToAnswer(request));
=======
//        request.addQuestionId(questionId);
        Answer answer = answerService.createAnswer(questionId ,answerMapper.answerDtoPostToAnswer(request));
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("question-id") @Positive long questionId,
                                      @PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.Patch request) {
<<<<<<< HEAD
        request.addQuestionId(questionId);
        Answer answer = answerService.updateAnswer(answerId, answerMapper.answerDtoPatchToAnswer(request));
=======
//        request.addQuestionId(questionId);
        Answer answer = answerService.updateAnswer(questionId, answerId, answerMapper.answerDtoPatchToAnswer(request));
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
        URI location = UriCreator.createUri(ANSWER_DEFAULT_URL, answer.getAnswerId());

        return ResponseEntity.created(location).build();
    }
<<<<<<< HEAD
=======

    @DeleteMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("question-id") @Positive long questionId,
                                       @PathVariable("answerId") @Positive long answerId) {
        answerService.deleteAnswer(questionId, answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
