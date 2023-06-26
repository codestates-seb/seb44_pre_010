package com.rainbow.sof.domain.answer;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;
import com.rainbow.sof.domain.answer.controller.AnswerController;
import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.answer.mapper.AnswerMapper;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.config.SecurityConfiguration;
import com.rainbow.sof.helper.StubData;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Import({SecurityConfiguration.class, JwtTokenizer.class})
//@WebMvcTest(AnswerController.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private AnswerMapper answerMapper;
    private final String ANSWER_DEFAULT_URL = "/api/v1/questions";

    private String accessTokenForUser;
    @Autowired
    private JwtTokenizer jwtTokenizer;

    @BeforeAll
    public void init() {
        accessTokenForUser = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKeySting(), "USER");
    }

    @Test
    @DisplayName("답변 등록")
    void postAnswer() throws Exception {
        //given
        AnswerDto.Post request = (AnswerDto.Post) StubData.MockAnswer.getRequestBody(HttpMethod.POST);

        String jsonData = gson.toJson(request);

        given(answerMapper.answerDtoPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(Answer.builder().build());
        given(answerService.createAnswer(Mockito.anyLong() ,Mockito.any(Answer.class), Mockito.anyString())).willReturn(Answer.builder().answerId(1L).build());

        //when
        ResultActions actions =
                mockMvc.perform(
                                post(ANSWER_DEFAULT_URL + "/{question-id}" + "/answers", 1)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonData)
                )

                //then
                        .andExpect(status().isCreated())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("답변 등록 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("답변 등록")
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                                        )
                                                        .pathParameters(
                                                                parameterWithName("question-id").description("질문 식별자")
                                                        )
                                                        .requestFields(
                                                                fieldWithPath("content").type(JsonFieldType.STRING).description("답변 세부내용")
                                                        )
                                                        .responseFields()
                                                        .build()
                                        )
                                )
                        );
    }

    @Test
    @DisplayName("답변 수정")
    void patchAnswer() throws Exception {
        //given
        AnswerDto.Patch request = (AnswerDto.Patch) StubData.MockAnswer.getRequestBody(HttpMethod.PATCH);

        String jsonData = gson.toJson(request);

        given(answerMapper.answerDtoPatchToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(Answer.builder().build());
        given(answerService.updateAnswer(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(Answer.class), Mockito.anyString())).willReturn(Answer.builder().answerId(1L).build());

        //when
        ResultActions actions =
                mockMvc.perform(
                                patch(ANSWER_DEFAULT_URL + "/{question-id}/answers/{answer-id}", 1L, 1L)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonData)
                )
                //then
                        .andExpect(status().isCreated())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("답변 수정 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("답변 수정")
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                                        )
                                                        .pathParameters(
                                                                parameterWithName("question-id").description("질문 식별자"),
                                                                parameterWithName("answer-id").description("답변 식별자")
                                                        )
                                                        .requestFields(
                                                                fieldWithPath("content").type(JsonFieldType.STRING).description("답변 세부내용")
                                                        )
                                                        .responseFields()
                                                        .build()
                                        )
                                )
                        );
    }

    @Test
    @DisplayName("답변 삭제")
    void deleteAnswer() throws Exception {
        //given
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString());

        //when
        mockMvc.perform(
                        delete(ANSWER_DEFAULT_URL + "/{question-id}/answers/{answer-id}", 1L, 1L)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                )
        //then
                .andExpect(status().isNoContent())
                .andDo(MockMvcRestDocumentationWrapper.document("답변 삭제 예제",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("답변 삭제 예제")
                                        .requestHeaders(
                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                        )
                                        .pathParameters(
                                                parameterWithName("question-id").description("질문 식별자"),
                                                parameterWithName("answer-id").description("답변 식별자")
                                        )
                                        .build()
                        )
                ));
    }
}
