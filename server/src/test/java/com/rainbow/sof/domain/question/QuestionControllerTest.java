package com.rainbow.sof.domain.question;

import com.google.gson.Gson;
import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.mapper.QuestionMapper;
import com.rainbow.sof.domain.question.service.QuestionService;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.description;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
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
    private AnswerService answerService;


    @MockBean
    private AnswerService answerService;

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
<<<<<<< HEAD
=======


    @Test
    @DisplayName("Question을 가져온다(상세 조회)")
    void getQuestion() throws Exception {
        //given
        String title = "이것은 제목입니다만. 제목이요 제목 제목제목제목제20자리 넘나요?";
        String content = "이것은 내용입니다만. 내용이요. 내용인데요";

        Answer answer = Answer.builder()
                .answerId(1L)
                .content("ㅎㅇㅎㅇ")
                .build();

        Answer answer2 = Answer.builder()
                .answerId(2L)
                .content("ㅎㅇㅎㅇ2")
                .build();
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();
        Answer answer = Answer.builder()
                .answerId(1L)
                .content("ㅎㅇㅎㅇ")
                .build();

        Answer answer2 = Answer.builder()
                .answerId(2L)
                .content("ㅎㅇㅎㅇ2")
                .build();
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();
        Question question = Question.builder()
                .questionId(1L)
                .view(0)
                .answers(List.of(answer, answer2))
                .content(content)
                .title(title)
                .build();

        AnswerDto.Response answerDto = AnswerDto.Response.builder()
                .answerId(1L)
                .content("ㅎㅇㅎㅇ")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .questionId(1L)
                .build();

        AnswerDto.Response answer2Dto = AnswerDto.Response.builder()
                .answerId(2L)
                .content("ㅎㅇㅎㅇ2")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .questionId(1L)
                .build();

        QuestionDto.Response response = QuestionDto.Response.builder()
                .title(title)
                .questionId(1L)
                .content(content)
                .view(0)
                .user(user)
                .answers(List.of(answerDto, answer2Dto))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        given(service.findQuestion(Mockito.anyLong())).willReturn(question);
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(2L);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL + "/{question-id}", question.getQuestionId())
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.title").value(question.getTitle()))
                        .andExpect(jsonPath("$.data.content").value(question.getContent()))
                        .andExpect(jsonPath("$.data.view").value(question.getView()))
                        .andExpect(jsonPath("$.data.answers[0].content").value(answer.getContent()))
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

        given(service.updateQuestion(Mockito.anyLong(), Mockito.any(Question.class))).willReturn(question);
        given(mapper.questionToQuestionDtoResponse(Mockito.any(Question.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                                patch(QUESTION_DEFAULT_URL + "/{question-id}", 1)
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
                        delete(QUESTION_DEFAULT_URL + "/{question-id}", 1L)
                        // then
                ).andExpect(status().isNoContent())
                .andDo(MockMvcRestDocumentationWrapper.document("질문 삭제 예제",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("질문 삭제 예제")
                                                .pathParameters(
                                                        parameterWithName("question-id").description("질문 식별자")
                                                )
                                                .build()
                                )
                        )
                );
<<<<<<< HEAD
=======

    }

    @Test
    @DisplayName("Top Question 리스트를 가져온다.")
    void getQuestions() throws Exception {
        String title = "내용입니다내용입니다.내용입니다내용입니다.내용입니다내용입니다.";
        String content = "제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목";
        //given
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();

        QuestionDto.ListResponse response = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(0)
                .user(user)
                .questionId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();
        QuestionDto.ListResponse response2 = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(5)
                .user(user)
                .questionId(2L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();

        given(service.findQuestions()).willReturn(new ArrayList<>());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(List.of(response, response2));
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(0L);
        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL)
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
        String title = "내용입니다내용입니다.내용입니다내용입니다.내용입니다내용입니다.";
        String content = "제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목";
        //given
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();

        QuestionDto.ListResponse response = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(0)
                .user(user)
                .questionId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();
        QuestionDto.ListResponse response2 = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(5)
                .user(user)
                .questionId(2L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();

        given(service.findPageQuestions(Mockito.anyString(), Mockito.anyInt())).willReturn(Page.empty());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(List.of(response, response2));
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
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

    }

    @Test
    @DisplayName("Top Question 리스트를 가져온다.")
    void getQuestions() throws Exception {
        String title = "내용입니다내용입니다.내용입니다내용입니다.내용입니다내용입니다.";
        String content = "제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목";
        //given
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();

        QuestionDto.ListResponse response = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(0)
                .user(user)
                .questionId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();
        QuestionDto.ListResponse response2 = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(5)
                .user(user)
                .questionId(2L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();

        given(service.findQuestions()).willReturn(new ArrayList<>());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(List.of(response, response2));
        given(answerService.getAnswerCnt(Mockito.anyLong())).willReturn(0L);
        //when
        ResultActions actions =
                mockMvc.perform(
                                get(QUESTION_DEFAULT_URL)
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
        String title = "내용입니다내용입니다.내용입니다내용입니다.내용입니다내용입니다.";
        String content = "제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목제목입니다.제목입니다.제목";
        //given
        UserDto.QuestionResponse user = UserDto.QuestionResponse.builder()
                .userId(1L)
                .name("테스트용")
                .build();

        QuestionDto.ListResponse response = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(0)
                .user(user)
                .questionId(1L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();
        QuestionDto.ListResponse response2 = QuestionDto.ListResponse.builder()
                .title(title)
                .content(content)
                .view(5)
                .user(user)
                .questionId(2L)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .answerCnt(0)
                .build();

        given(service.findPageQuestions(Mockito.anyString(), Mockito.anyInt())).willReturn(Page.empty());
        given(mapper.questionToQuestionDtoResponseList(Mockito.anyList())).willReturn(List.of(response, response2));
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

>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
