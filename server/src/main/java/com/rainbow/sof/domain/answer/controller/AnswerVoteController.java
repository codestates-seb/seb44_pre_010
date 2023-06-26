package com.rainbow.sof.domain.answer.controller;

import com.rainbow.sof.domain.answer.dto.AnswerVoteDto;
import com.rainbow.sof.domain.answer.service.AnswerVoteService;
import com.rainbow.sof.global.common.AuthenticationName;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@RequiredArgsConstructor
@RequestMapping("/api/v1/answers")
@RestController
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;

    @PostMapping("/{answer-id}/vote")
    public ResponseEntity postAnswerVote(@PathVariable("answer-id") @Positive long answerId,
                                         @RequestParam("status") String voteStatus,
                                         @AuthenticationName String email) {
        int answerVoteCnt = answerVoteService.createAnswerVote(answerId, voteStatus, email);
        AnswerVoteDto.Response response = new AnswerVoteDto.Response(answerVoteCnt);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{answer-id}/vote")
    public ResponseEntity deleteAnswerVote(@PathVariable("answer-id") @Positive long answerId,
                                           @AuthenticationName String email) {
        int answerVoteCnt = answerVoteService.deleteAnswerVote(answerId, email);
        AnswerVoteDto.Response response = new AnswerVoteDto.Response(answerVoteCnt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
