package com.rainbow.sof.domain.question;

import com.google.gson.Gson;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private QuestionService service;
    @MockBean
    private QuestionMapper mapper;

    private final String QUESTION_DEFAULT_URL = "/api/v1/questions";

    @Test
    @DisplayName("Question을 추가한다.")
    void postQuestion() throws Exception {
        //given
        String title = "이것은 제목입니다만. 제목이요 제목";
        String content = "이것은 내용입니다만. 내용이요. 내용";
        QuestionDto.Post request = QuestionDto.Post.builder()
                .title(title)
                .content(content)
                .userId(1)
                .build();

        String jsonData = gson.toJson(request);

        given(mapper.questionDtoPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(Question.builder().build());

        Question question = Question.builder()
                .questionId(1L)
                .view(0)
                .content(content)
                .title(title)
                .build();

        QuestionDto.Response response = QuestionDto.Response.builder()
                .title(title)
                .content(content)
                .view(0)
                .questionId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        given(service.createQuestion(Mockito.any(Question.class))).willReturn(question);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                post(QUESTION_DEFAULT_URL)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonData)
                        )
                        //then
<<<<<<< HEAD
                        .andExpect(status().isCreated());
=======
                        .andExpect(status().isCreated())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 등록 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 등록")
                                                        .requestFields(
                                                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("작성자 아이디"),
                                                                fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("content").type(JsonFieldType.STRING).description("질문 세부내용")
                                                        )
                                                        .responseFields()
                                                        .build()
                                        )
                                )
                        );
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1

    }
}
