package com.rainbow.sof.domain.question.controller;

import com.rainbow.sof.domain.question.domain.QuestionVote;
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
        QuestionVoteDto.Response response = QuestionVoteDto.Response.builder()
                .vote(questionVoteCnt)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
