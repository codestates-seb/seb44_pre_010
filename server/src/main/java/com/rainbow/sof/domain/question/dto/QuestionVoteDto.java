package com.rainbow.sof.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class QuestionVoteDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response{
        private int vote;
    }
}
