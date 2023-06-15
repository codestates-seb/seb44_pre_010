package com.rainbow.sof.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class QuestionDto {
    private QuestionDto() {
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Post{
        private String title;
        private String content;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Patch{
        private String title;
        private String content;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Response{
        // TODO: USER 정보는 추후에 추가
        private Long questionId;
        private String title;
        private String content;
        private int view;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
