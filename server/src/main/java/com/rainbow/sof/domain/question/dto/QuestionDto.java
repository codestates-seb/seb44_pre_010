package com.rainbow.sof.domain.question.dto;

import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.global.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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


    /**
     * myPage response 클래스
     *  @author 신영호
     **/
   @Getter
   @AllArgsConstructor
   @Builder
    public static class MyPageQuestionResponse{
        private Long questionId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

    }


}
