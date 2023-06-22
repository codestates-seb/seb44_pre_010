package com.rainbow.sof.domain.question.controller;

import com.rainbow.sof.domain.question.dto.QuestionVoteDto;
import com.rainbow.sof.domain.question.service.QuestionVoteService;
import com.rainbow.sof.global.common.AuthenticationName;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
@RestController
public class QuestionVoteController {
    private final QuestionVoteService questionVoteService;
    @PostMapping("{question_id}/vote")
    public ResponseEntity postQuestionVote(@PathVariable("question_id") @Positive long id, @RequestParam("status") String voteStatus
                                            ,@AuthenticationName String email){
        int questionVoteCnt = questionVoteService.createQuestionVote(email, id, voteStatus);
        QuestionVoteDto.Response response = new QuestionVoteDto.Response(questionVoteCnt);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{question_id}/vote")
    public ResponseEntity deleteQuestionVote(@PathVariable("question_id") @Positive long id, @AuthenticationName String email){
        int questionVoteCnt = questionVoteService.deleteQuestionVote(email, id);
        QuestionVoteDto.Response response = new QuestionVoteDto.Response(questionVoteCnt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
