package com.rainbow.sof.helper;

import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubData {

    public static class MockUser {
        public static UserDto.QuestionResponse getSingleResponseBody() {
            return UserDto.QuestionResponse.builder()
                    .userId(1L)
                    .name("테스트용")
                    .build();
        }
    }

    public static class MockQuestion {
        private static final String title = "이것은 제목입니다만. 제목이요 제목 제목제목제목제20자리 넘나요?";
        private static final String content = "이것은 메소드내용입니다만. 내용이요. 내용";
        private static Map<HttpMethod, Object> stubRequestBody;

        static {
            stubRequestBody = new HashMap<>();
            QuestionDto.Post post = QuestionDto.Post.builder()
                    .title(title)
                    .content(content)
                    .build();
            stubRequestBody.put(HttpMethod.POST, post);

            QuestionDto.Patch patch = QuestionDto.Patch.builder()
                    .title("이것은 제목입니다만. 제목이요 제목 제목제목제목제20자리 넘나요?")
                    .content("이것은 메소드내용입니다만. 내용이요. 내용")
                    .build();
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static QuestionDto.Response getSingleResponseBody() {
            return QuestionDto.Response.builder()
                    .title(title)
                    .content(content)
                    .view(0)
                    .questionId(1L)
                    .user(StubData.MockUser.getSingleResponseBody())
                    .answerCnt(StubData.MockAnswer.getMultiResponseBody().size())
                    .answers(StubData.MockAnswer.getMultiResponseBody())
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();
        }

        public static List<QuestionDto.ListResponse> getMultiResponseBody() {
            return List.of(
                    QuestionDto.ListResponse.builder()
                            .title(title)
                            .content(content)
                            .view(0)
                            .user(StubData.MockUser.getSingleResponseBody())
                            .questionId(1L)
                            .createdAt(LocalDateTime.now())
                            .modifiedAt(LocalDateTime.now())
                            .answerCnt(0)
                            .build(),
                    QuestionDto.ListResponse.builder()
                            .title(title)
                            .content(content)
                            .view(5)
                            .user(StubData.MockUser.getSingleResponseBody())
                            .questionId(2L)
                            .createdAt(LocalDateTime.now())
                            .modifiedAt(LocalDateTime.now())
                            .answerCnt(0)
                            .build()
            );
        }
    }

    public static class MockAnswer {
        private final static String content = "이건 댓글의 내용입니다. 내용이요. 내용.";
        private static Map<HttpMethod, Object> stubRequestBody;

        static {
            stubRequestBody = new HashMap<>();
            AnswerDto.Post post = AnswerDto.Post.builder()
                    .questionId(1L)
                    .content(content)
                    .build();
            stubRequestBody.put(HttpMethod.POST, post);
            AnswerDto.Patch patch = AnswerDto.Patch.builder()
                    .questionId(1L)
                    .content(content)
                    .answerId(1L)
                    .build();
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static List<AnswerDto.Response> getMultiResponseBody() {
            return List.of(
                    AnswerDto.Response.builder()
                            .answerId(1L)
                            .content("ㅎㅇㅎㅇ11")
                            .createdAt(LocalDateTime.now())
                            .modifiedAt(LocalDateTime.now())
                            .questionId(1L)
                            .build(),

                    AnswerDto.Response.builder()
                            .answerId(2L)
                            .content("ㅎㅇㅎㅇ22")
                            .createdAt(LocalDateTime.now())
                            .modifiedAt(LocalDateTime.now())
                            .questionId(1L)
                            .build()
            );
        }
    }
}
