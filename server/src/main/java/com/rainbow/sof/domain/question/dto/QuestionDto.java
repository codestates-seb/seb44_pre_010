package com.rainbow.sof.domain.question.dto;

import com.rainbow.sof.global.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class QuestionDto {
    private QuestionDto() {
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Post{
        @NotSpace(message = "질문 제목은 공백이 아니어야 합니다.")
        @Size(min= 20, max= 100)
        private String title;
        @NotSpace(message = "질문 세부내용은 공백이 아니어야 합니다.")
        @Size(min= 2, max= 220)
        private String content;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Patch{
        @NotSpace(message = "질문 제목은 공백이 아니어야 합니다.")
        @Size(min= 20, max= 100)
        private String title;
        @NotSpace(message = "질문 세부내용은 공백이 아니어야 합니다.")
        @Size(min= 2, max= 220)
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
