package com.rainbow.sof.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1
import java.time.LocalDateTime;

public class QuestionDto {
    private QuestionDto() {
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Post{
<<<<<<< HEAD
=======
        @NotNull
        private long userId;
        @NotSpace(message = "질문 제목은 공백이 아니어야 합니다.")
        @Size(min= 20, max= 100)
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1
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
