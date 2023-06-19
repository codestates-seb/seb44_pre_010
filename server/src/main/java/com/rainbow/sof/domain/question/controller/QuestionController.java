package com.rainbow.sof.domain.question.controller;

import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
<<<<<<< HEAD
import com.rainbow.sof.global.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
=======
import com.rainbow.sof.global.common.MultiResponseDto;
import com.rainbow.sof.global.common.SingleResponseDto;
import com.rainbow.sof.global.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
@RestController
public class QuestionController {

    private final static String QUESTION_DEFAULT_URL = "/api/v1/questions";
<<<<<<< HEAD
    final private QuestionService questionService;
    final private QuestionMapper questionMapper;
    @PostMapping("/questions")
    public ResponseEntity saveQuestion(@RequestBody QuestionDto.Post request){
=======
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionMapper questionMapper;
    @PostMapping()
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post request){
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
        Question question = questionService.createQuestion(questionMapper.questionDtoPostToQuestion(request));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }
<<<<<<< HEAD
=======

    @GetMapping
    public ResponseEntity getQuestions(){
        List<Question> questions = questionService.findQuestions();
        List<QuestionDto.ListResponse> responses = questionMapper.questionToQuestionDtoResponseList(questions);
        responses.forEach(o -> o.setAnswerCnt(answerService.getAnswerCnt(o.getQuestionId())));
        return new ResponseEntity(
                new SingleResponseDto<>(responses), HttpStatus.OK);
    }

    @GetMapping(params = {"tab","page"})
    public ResponseEntity getQuestions(@RequestParam(name = "tab", defaultValue = "Newest")String sort,
                                       @RequestParam(name = "page", defaultValue = "1")int page){
        Page<Question> pageQuestions = questionService.findPageQuestions(sort, page); // 30, 5
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.ListResponse> responses = questionMapper.questionToQuestionDtoResponseList(questions);
        responses.forEach(o -> o.setAnswerCnt(answerService.getAnswerCnt(o.getQuestionId())));

        return new ResponseEntity(
                new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestion(@PathVariable("id") @Positive long id){
        Question question = questionService.findQuestion(id);
        QuestionDto.Response response = questionMapper.questionToQuestionDtoResponse(question);
        response.setAnswerCnt(answerService.getAnswerCnt(question.getQuestionId()));
        return new ResponseEntity(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchQuestion(@PathVariable("id") @Positive long id
                                            ,@RequestBody @Valid QuestionDto.Patch request){
        Question question = questionService.updateQuestion(id, questionMapper.questionDtoPatchToQuestion(request));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") @Positive long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
