package com.rainbow.sof.domain.answer.dto;


import com.rainbow.sof.global.validator.NotSpace;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AnswerDto {
    private AnswerDto() {

    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Post {
        private long questionId;
        @NotSpace(message = "답변 세부내용은 공백이 아니어야 합니다.")
        @Size(min = 30, max = 220, message = "답변 세부내용은 30자 이상이어야 합니다.")
        private String content;
        public void addQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Patch {
        private long questionId;
        private long answerId;
        @NotSpace(message = "답변 세부내용은 공백이 아니어야 합니다.")
        @Size(min = 30, max = 220, message = "답변 세부내용은 30자 이상이어야 합니다.")
        private String content;

        public void addQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private Long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        @Setter(AccessLevel.NONE)
        private Long questionId;

        // TODO: Users 추후에 추가
    }
}