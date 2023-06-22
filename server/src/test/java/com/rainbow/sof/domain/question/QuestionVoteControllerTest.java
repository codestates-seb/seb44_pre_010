package com.rainbow.sof.domain.question;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

import com.rainbow.sof.domain.question.controller.QuestionVoteController;

import com.rainbow.sof.domain.question.service.QuestionVoteService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.config.SecurityConfiguration;
import com.rainbow.sof.helper.StubData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfiguration.class, JwtTokenizer.class})
@WebMvcTest(QuestionVoteController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionVoteService service;
    private String accessTokenForUser;
    @Autowired
    private JwtTokenizer jwtTokenizer;

    private final String QUESTION_VOTE_DEFAULT_URL = "/api/v1/questions";

    @BeforeAll
    public void init() {
        accessTokenForUser = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKeySting(), "USER");
    }

    @Test
    @DisplayName("질문에 추천을 한다.")
    void posVote() throws Exception {
        //given
        given(service.createQuestionVote(Mockito.anyString(),Mockito.anyLong(),Mockito.anyString())).willReturn(1);
        //when
        ResultActions actions =
                mockMvc.perform(
                                post(QUESTION_VOTE_DEFAULT_URL+"/{question_id}/vote",1L)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                                        .param("status","up")
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.vote").value(1))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 투표 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 투표")
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                                        )
                                                        .pathParameters(
                                                                parameterWithName("question_id").description("질문 식별자")
                                                        )
                                                        .requestParameters(
                                                                parameterWithName("status").description("질문 상태(up/down)")
                                                        )
                                                        .responseFields(
                                                                fieldWithPath("vote").type(JsonFieldType.NUMBER).description("질문 투표 수")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

    @Test
    @DisplayName("질문에 했던 추천을 취소한다.")
    void deleteVote() throws Exception {
        //given
        given(service.deleteQuestionVote(Mockito.anyString(),Mockito.anyLong())).willReturn(0);
        //when
        ResultActions actions =
                mockMvc.perform(
                                delete(QUESTION_VOTE_DEFAULT_URL+"/{question_id}/vote",1L)
                                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        //then
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.vote").value(0))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("질문 투표 취소 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("질문 투표 취소")
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                                        )
                                                        .pathParameters(
                                                                parameterWithName("question_id").description("질문 식별자")
                                                        )
                                                        .responseFields(
                                                                fieldWithPath("vote").type(JsonFieldType.NUMBER).description("질문 투표 수")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

}
