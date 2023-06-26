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
        @NotSpace(message = "답변 세부내용은 공백이 아니어야 합니다.")
        @Size(min = 30, max = 220, message = "답변 세부내용은 30자 이상, 220자 미만이어야 합니다.")
        private String content;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Patch {
        @NotSpace(message = "답변 세부내용은 공백이 아니어야 합니다.")
        @Size(min = 30, max = 220, message = "답변 세부내용은 30자 이상, 220자 미만이어야 합니다.")
        private String content;
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
        private long vote;

        @Setter(AccessLevel.NONE)
        private Long questionId;
    }

    /**
     * @comment myPageResponse 을 위한 클래스입니다.
     * @author 신영호
     */
    @Getter
    @Builder
    public static class MyPageAnswerResponse{
        private Long answerId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
