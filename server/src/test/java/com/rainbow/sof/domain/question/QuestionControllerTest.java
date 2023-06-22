package com.rainbow.sof.domain.question;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;
import com.rainbow.sof.domain.answer.service.AnswerService;

import com.rainbow.sof.domain.question.controller.QuestionController;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.config.SecurityConfiguration;
import com.rainbow.sof.helper.StubData;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfiguration.class, JwtTokenizer.class})
@WebMvcTest(QuestionController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private QuestionService service;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private QuestionMapper mapper;
    private final String QUESTION_DEFAULT_URL = "/api/v1/questions";

    private String accessTokenForUser;
    @Autowired
    private JwtTokenizer jwtTokenizer;

    @Autowired
    private WebApplicationContext ctx;
    @BeforeAll
    public void init() {
        accessTokenForUser = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKeySting(), "USER");
    }
    @Test
    @WithMockUser
    @DisplayName("Question을 추가한다.(저장)")
    void postQuestion() throws Exception {
        //given
        QuestionDto.Post request = (QuestionDto.Post) StubData.MockQuestion.getRequestBody(HttpMethod.POST);

        String jsonData = gson.toJson(request);

        given(mapper.questionDtoPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(Question.builder().build());
        given(service.createQuestion(Mockito.any(Question.class),Mockito.anyString())).willReturn(Question.builder().questionId(1L).build());

        //when
        ResultActions actions =
                mockMvc.perform(
                                post(QUESTION_DEFAULT_URL)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
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
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
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
    @DisplayName("Question을 가져온다(상세 조회)")
    void getQuestion() throws Exception {
        //given
        QuestionDto.Response response = StubData.MockQuestion.getSingleResponseBody();

        given(service.findQuestion(Mockito.anyLong())).willReturn(Question.builder().questionId(1L).build());
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(2L);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL + "/{question-id}", 1L)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.title").value(response.getTitle()))
                        .andExpect(jsonPath("$.data.content").value(response.getContent()))
                        .andExpect(jsonPath("$.data.view").value(response.getView()))
                        .andExpect(jsonPath("$.data.answers[0].content").value(response.getAnswers().get(0).getContent()))
                        .andExpect(jsonPath("$.data.answerCnt").value(2))
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
                                                                fieldWithPath("data.user").type(JsonFieldType.OBJECT).description("회원 정보"),
                                                                fieldWithPath("data.user.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                                fieldWithPath("data.user.name").type(JsonFieldType.STRING).description("회원 닉네임"),
                                                                fieldWithPath("data.answers").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                                                fieldWithPath("data.answers.[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                                                fieldWithPath("data.answers.[].content").type(JsonFieldType.STRING).description("답변 세부내용"),
                                                                fieldWithPath("data.answers.[].createdAt").type(JsonFieldType.STRING).description("답변 작성일"),
                                                                fieldWithPath("data.answers.[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정일"),
                                                                fieldWithPath("data.answers.[].questionId").type(JsonFieldType.NUMBER).description("답변의 질문 식별자"),
                                                                fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                                                fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("data.content").type(JsonFieldType.STRING).description("질문 내용"),
                                                                fieldWithPath("data.view").type(JsonFieldType.NUMBER).description("조회수"),
                                                                fieldWithPath("data.answerCnt").type(JsonFieldType.NUMBER).description("답변 개수"),
                                                                fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표 개수"),
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
        QuestionDto.Patch request = (QuestionDto.Patch) StubData.MockQuestion.getRequestBody(HttpMethod.PATCH);

        String jsonData = gson.toJson(request);

        given(mapper.questionDtoPatchToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(Question.builder().build());
        given(service.updateQuestion(Mockito.anyLong(), Mockito.any(Question.class),Mockito.anyString())).willReturn(Question.builder().questionId(1L).build());

        //when
        ResultActions actions =
                mockMvc.perform(
                                patch(QUESTION_DEFAULT_URL + "/{question-id}", 1)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(accessTokenForUser))
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
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
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
        doNothing().when(service).deleteQuestion(Mockito.anyLong(), Mockito.anyString());
        // when
        mockMvc.perform(
                        delete(QUESTION_DEFAULT_URL + "/{question-id}", 1L)
                                .header("Authorization", "Bearer ".concat(accessTokenForUser))
                        // then
                ).andExpect(status().isNoContent())
                .andDo(MockMvcRestDocumentationWrapper.document("질문 삭제 예제",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("질문 삭제 예제")
                                                .requestHeaders(
                                                        headerWithName("Authorization").description("발급받은 인증 토큰")
                                                )
                                                .pathParameters(
                                                        parameterWithName("question-id").description("질문 식별자")
                                                )
                                                .build()
                                )
                        )
                );

    }

    @Test
    @DisplayName("Top Question 리스트를 가져온다.")
    void getQuestions() throws Exception {
        //given
        given(service.findQuestions()).willReturn(new ArrayList<>());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(StubData.MockQuestion.getMultiResponseBody());
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(0L);
        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL + "/top")
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.size()").value(2))
                        .andExpect(jsonPath("$.data[1].view").value(5))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 리스트 조회(Top Question) 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 리스트 조회(Top Question)")
                                                        .requestFields()
                                                        .responseFields(
                                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                                                fieldWithPath("data.[].user").type(JsonFieldType.OBJECT).description("회원 정보"),
                                                                fieldWithPath("data.[].user.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                                fieldWithPath("data.[].user.name").type(JsonFieldType.STRING).description("회원 닉네임"),
                                                                fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                                                fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("질문 내용"),
                                                                fieldWithPath("data.[].view").type(JsonFieldType.NUMBER).description("조회수"),
                                                                fieldWithPath("data.[].answerCnt").type(JsonFieldType.NUMBER).description("답변 개수"),
                                                                fieldWithPath("data.[].vote").type(JsonFieldType.NUMBER).description("투표 개수"),
                                                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("작성일"),
                                                                fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("수정일")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

    @Test
    @DisplayName("페이지 네이션이 적용된 리스트를 가져온다.")
    void getPageQuestions() throws Exception {
        //given
        given(service.findPageQuestions(Mockito.anyString(), Mockito.anyInt())).willReturn(Page.empty());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(StubData.MockQuestion.getMultiResponseBody());
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(0L);
        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL)
                                        .param("tab", "Newest")
                                        .param("page","1")
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.size()").value(2))
                        .andExpect(jsonPath("$.pageInfo.page").value(1))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 페이징 리스트 조회 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 페이징 리스트 조회")
                                                        .requestParameters(
                                                                parameterWithName("tab").description("정렬"),
                                                                parameterWithName("page").description("페이징")
                                                        )
                                                        .requestFields()
                                                        .responseFields(
                                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                                                fieldWithPath("data.[].user").type(JsonFieldType.OBJECT).description("회원 정보"),
                                                                fieldWithPath("data.[].user.userId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                                fieldWithPath("data.[].user.name").type(JsonFieldType.STRING).description("회원 닉네임"),
                                                                fieldWithPath("data.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                                                fieldWithPath("data.[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("data.[].content").type(JsonFieldType.STRING).description("질문 내용"),
                                                                fieldWithPath("data.[].view").type(JsonFieldType.NUMBER).description("조회수"),
                                                                fieldWithPath("data.[].answerCnt").type(JsonFieldType.NUMBER).description("답변 개수"),
                                                                fieldWithPath("data.[].vote").type(JsonFieldType.NUMBER).description("투표 개수"),
                                                                fieldWithPath("data.[].createdAt").type(JsonFieldType.STRING).description("작성일"),
                                                                fieldWithPath("data.[].modifiedAt").type(JsonFieldType.STRING).description("수정일"),
                                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이징 정보"),
                                                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("한 페이지에 속하는 데이터 개수"),
                                                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 데이터 개수"),
                                                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 개수")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

}
