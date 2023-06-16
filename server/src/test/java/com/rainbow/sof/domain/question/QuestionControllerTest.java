package com.rainbow.sof.domain.question;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
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
    @DisplayName("Question을 추가한다.(저장)")
    void postQuestion() throws Exception {
        //given
        String title = "이것은 제목입니다만. 제목이요 제목 제목제목제목제20자리 넘나요?";
        String content = "이것은 내용입니다만. 내용이요. 내용";
        QuestionDto.Post request = QuestionDto.Post.builder()
                .title(title)
                .content(content)
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
                        .andExpect(status().isCreated())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 등록 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 등록")
                                                        .requestFields(
                                                                fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("content").type(JsonFieldType.STRING).description("질문 세부내용")
                                                        )
                                                        .responseFields()
                                                        .build()
                                        )
                                )
                        );

    }


    @Test
    @DisplayName("Question을 가져온다(상세 조회)")
    void getQuestion() throws Exception {
        //given
        String title = "이것은 제목입니다만. 제목이요 제목 제목제목제목제20자리 넘나요?";
        String content = "이것은 내용입니다만. 내용이요. 내용인데요";

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

        given(service.findQuestion(Mockito.anyLong())).willReturn(question);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL + "/{question-id}",question.getQuestionId())
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.title").value(question.getTitle()))
                        .andExpect(jsonPath("$.data.content").value(question.getContent()))
                        .andExpect(jsonPath("$.data.view").value(question.getView()))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 상세 조회 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 상세 조회")
                                                        .pathParameters(
                                                                parameterWithName("question-id").description("질문 식별자")
                                                        )
                                                        .requestFields()
                                                        .responseFields(
                                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                                fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("질문 내용"),
                                                                fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
                                                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성일"),
                                                                fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정일")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

    @Test
    @DisplayName("Question이 수정된다.(수정)")
    void patchQuestion() throws Exception {
        //given
        String title = "이것은 제목입니다만. 제목이요 제목 제목제목ddddd제목제20자리 넘나요?";
        String content = "이것은 내용dddddd입니다만. 내용이요. 내용";
        QuestionDto.Patch request = QuestionDto.Patch.builder()
                .title(title)
                .content(content)
                .build();

        String jsonData = gson.toJson(request);

        given(mapper.questionDtoPatchToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(Question.builder().build());

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

        given(service.updateQuestion(Mockito.anyLong(),Mockito.any(Question.class))).willReturn(question);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                patch(QUESTION_DEFAULT_URL + "/{question-id}",1)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonData)
                        )
                        //then
                        .andExpect(status().isCreated())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 수정 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 수정")
                                                        .pathParameters(
                                                                parameterWithName("question-id").description("질문 식별자")
                                                        )
                                                        .requestFields(
                                                                fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("content").type(JsonFieldType.STRING).description("질문 세부내용")
                                                        )
                                                        .responseFields()
                                                        .build()
                                        )
                                )
                        );

    }

    @Test
    @DisplayName("Question이 삭제된다.(삭제)")
    void deleteQuestion() throws Exception {
        //given
        doNothing().when(service).deleteQuestion(Mockito.anyLong());
        // when
        mockMvc.perform(
                        delete(QUESTION_DEFAULT_URL+"/{question-id}",1L)
                // then
                ).andExpect(status().isNoContent())
                .andDo(MockMvcRestDocumentationWrapper.document("질문 삭제 예제",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        )
                ));

    }

}
