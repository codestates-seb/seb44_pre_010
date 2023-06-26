package com.rainbow.sof.domain.answer;


import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.rainbow.sof.domain.answer.controller.AnswerVoteController;
import com.rainbow.sof.domain.answer.service.AnswerVoteService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.config.SecurityConfiguration;
import com.rainbow.sof.helper.StubData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Import({SecurityConfiguration.class, JwtTokenizer.class})
//@WebMvcTest(AnswerVoteController.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerVoteService answerVoteService;
    private String accessTokenForUser;
    @Autowired
    private JwtTokenizer jwtTokenizer;

    private final String ANSWER_VOTE_DEFAULT_URL = "/api/v1/answers";

    @BeforeAll
    public void init() {
        accessTokenForUser = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKeySting(), "USER");
    }

    @Test
    @DisplayName("답변 추천")
    void PostAnswerVote() throws Exception {
        //given
        BDDMockito.given(answerVoteService.createAnswerVote(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .willReturn(1);

        //when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post(ANSWER_VOTE_DEFAULT_URL + "/{answer-id}/vote", 1L)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessTokenForUser)
                                .param("status", "up")
                                .accept(MediaType.APPLICATION_JSON)
                )

                //then
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.vote").value(1))
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("답변 투표 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("답변 투표")
                                                        .requestHeaders(
                                                                headerWithName("Authorization").description("발급받은 인증 토큰")
                                                        )
                                                        .pathParameters(
                                                                parameterWithName("answer-id").description("답변 식별자")
                                                        )
                                                        .requestParameters(
                                                                parameterWithName("status").description("질문 상태(up/down)")
                                                        )
                                                        .responseFields(
                                                                fieldWithPath("vote").type(JsonFieldType.NUMBER).description("답변 투표 수")
                                                        )
                                                        .build()
                                        )
                                )
                        );
    }
}
