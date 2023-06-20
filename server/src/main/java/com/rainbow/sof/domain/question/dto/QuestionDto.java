package com.rainbow.sof.domain.question.dto;

import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.global.validator.NotSpace;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    private QuestionDto() {
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Post{
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
        private UserDto.QuestionResponse user;
        private List<AnswerDto.Response> answers;
        private Long questionId;
        private String title;
        private String content;
        private int view;
        private long answerCnt;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public void setAnswerCnt(long answerCnt){
            this.answerCnt = answerCnt;
        }
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ListResponse{
        private UserDto.QuestionResponse user;
        private Long questionId;
        private String title;
        private String content;
        private int view;
        private long answerCnt;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public void setAnswerCnt(long answerCnt){
            this.answerCnt = answerCnt;
        }
    }
}
