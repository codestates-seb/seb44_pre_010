package com.rainbow.sof.domain.question.controller;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
import com.rainbow.sof.global.common.SingleResponseDto;
import com.rainbow.sof.global.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class QuestionController {

    private final static String QUESTION_DEFAULT_URL = "/api/v1/questions";
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    @PostMapping("/questions")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post request){
        Question question = questionService.createQuestion(questionMapper.questionDtoPostToQuestion(request));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity saveQuestion(@PathVariable("id") long id){
        Question question = questionService.findQuestion(id);

        return new ResponseEntity(
                new SingleResponseDto<>(questionMapper.questionToQuestionDtoResponse(question)), HttpStatus.OK);
    }
}
