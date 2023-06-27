package com.rainbow.sof.domain.user.controller;


import com.epages.restdocs.apispec.*;
import com.google.gson.Gson;
import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.user.dto.DataDto.UserDataResponse;
import com.rainbow.sof.domain.user.dto.UserToJoinDto.MyPageResponseDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.mapper.UserMapper;
import com.rainbow.sof.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class UserControllerTest {
    private final String User_ORIGIN_URI ="/api/v1";
    private final String User_USERDATA_ORIGIN_URI ="/api/v1/users";

    @Autowired
    private MockMvc mockMvc;


    private final Gson gson = new Gson();

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    private String userContext;

    private User testUserEntity;

    @BeforeEach
    public void initTest(){
        List<Question> questionList =new ArrayList<>();
        questionList.add(
                Question.builder()
                        .title("타이틀입니다타이틀입니다타이틀입니다타이틀입니다")
                        .content("내용입니다. 내용입니다. 내용입니다. 내용입니다. 내용입니다.")
                        .questionId(1L)
                        .build());
        List<Answer> answerList =new ArrayList<>();
        answerList.add(
                Answer.builder()
                        .content("내용입니다. 내용입니다. 내용입니다. 내용입니다. 내용입니다.")
                        .answerId(1L)
                        .build());
        this.testUserEntity = User.builder()
                .userId(1L)
                .name("홍길동")
                .email("test@test.com")
                .password("q12341234")
                .questionList(questionList)
                .answerList(answerList)
                .build();

        this.userContext = gson.toJson(UserDto.SignUpPost.builder()
                .name("홍길동").
                password("q12341234").
                email("test@test.com").
                build());
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void createUserTest() throws Exception{

        given(service.createUser(Mockito.any(User.class))).willReturn(testUserEntity);
        given(mapper.userSignupPostToUser(Mockito.any(UserDto.SignUpPost.class))).willReturn(testUserEntity);

        //MockMvcRequestBuilders 대신 RestDocumentationRequestBuilders 를 사용해야 문서화가 됨
        mockMvc.perform(RestDocumentationRequestBuilders.post(User_ORIGIN_URI+"/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userContext))

                .andExpect(MockMvcResultMatchers.status().isCreated())


                .andDo(
                        document("회원 가입 예제",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("회원 가입")
                                                .requestFields(
                                                        fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름"),
                                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                                        fieldWithPath("email").type(JsonFieldType.STRING).description("사용자 이메일")
                                                )
                                                .responseFields()
                                                .build()
                                )
                        )
                );
    }

    @Test
    @WithMockUser
    @DisplayName("회원 정보 확인 테스트")
    public void getUserTest() throws Exception{
        QuestionDto.MyPageQuestionResponse response = getResponse();
        AnswerDto.MyPageAnswerResponse answerResponse= getAnswer();

        MyPageResponseDto responseDto = MyPageResponseDto.builder()
                .name("홍길동").createdAt(LocalDateTime.now())
                .questionList(List.of(response))
                .AnswerList(List.of(answerResponse))
                .build();


        given(service.checkToFindByUserFromEmail(Mockito.anyString(),Mockito.anyLong())).willReturn(testUserEntity);
        given(mapper.userToMyPageDto(Mockito.any(User.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get(User_USERDATA_ORIGIN_URI +"/{user-id}",testUserEntity.getUserId())
                                .header(HttpHeaders.AUTHORIZATION, "\u200BBearer {ACCESS_TOKEN}") // ​ <- 특수 공백문자
                                .accept(MediaType.APPLICATION_JSON))

                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(
                                MockMvcRestDocumentationWrapper.document("회원 정보 확인 예제",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        resource(
                                                ResourceSnippetParameters.builder()
                                                        .description("회원 정보 확인")
                                                        .pathParameters(
                                                                parameterWithName("user-id").description("회원 식별자")
                                                        )
                                                        .requestHeaders(
                                                                headerWithName(HttpHeaders.AUTHORIZATION).description("발급 받은 인증 토큰")
                                                        )
                                                        .requestFields()
                                                        .responseFields(
                                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("회원 이름"),
                                                                fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("회원 가입일"),
                                                                fieldWithPath("data.questionList").type(JsonFieldType.ARRAY).description("질문 리스트"),
                                                                fieldWithPath("data.questionList.[].questionId").type(JsonFieldType.NUMBER).description("질문 식별Id"),
                                                                fieldWithPath("data.questionList.[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                                fieldWithPath("data.questionList.[].content").type(JsonFieldType.STRING).description("질문 내용"),
                                                                fieldWithPath("data.questionList.[].createdAt").type(JsonFieldType.STRING).description("질문 등록일"),
                                                                fieldWithPath("data.questionList.[].modifiedAt").type(JsonFieldType.STRING).description("질문 수정일"),
                                                                fieldWithPath("data.answerList").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                                                fieldWithPath("data.answerList.[].answerId").type(JsonFieldType.NUMBER).description("답변 식별Id"),
                                                                fieldWithPath("data.answerList.[].content").type(JsonFieldType.STRING).description("답변 내용"),
                                                                fieldWithPath("data.answerList.[].createdAt").type(JsonFieldType.STRING).description("답변 등록일"),
                                                                fieldWithPath("data.answerList.[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정일")
                                                        )
                                                        .build()
                                        )
                                )
                        );

    }

    @Test
    @WithMockUser
    @DisplayName("회원 정보 수정 테스트")
    public void patchUserTest() throws Exception {
        String context = gson.toJson(getPatchResponse());

        given(service.updateUser(
                Mockito.anyString(),Mockito.anyLong(),Mockito.any(UserDto.Patch.class))
        ).willReturn(testUserEntity);

        mockMvc.perform(RestDocumentationRequestBuilders.patch(User_USERDATA_ORIGIN_URI +"/{user-id}",testUserEntity.getUserId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer {ACCESS_TOKEN}")
                        .content(context)
                )

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentationWrapper.document("회원 정보 수정 테스트",
                                preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("회원 정보 수정")
                                                .pathParameters(
                                                        parameterWithName("user-id").description("회원 식별자")
                                                )
                                                .requestHeaders(
                                                        headerWithName(HttpHeaders.AUTHORIZATION).description("발급 받은 인증 토큰")
                                                )
                                                .build()
                                )
                        )
                );

    }

    private UserDto.Patch getPatchResponse() {
        return UserDto.Patch.builder().name("박첨지").build();
    }


    @Test
    @WithMockUser
    @DisplayName("회원 탈퇴 테스트")
    public void deleteUserTest() throws Exception{
        given(service.checkToFindByUserFromEmail(Mockito.anyString(),Mockito.anyLong())).willReturn(testUserEntity);
        doNothing().when(service).deleteUser(Mockito.anyString(),Mockito.anyLong());
        mockMvc.perform(RestDocumentationRequestBuilders.delete(User_USERDATA_ORIGIN_URI +"/{user-id}",testUserEntity.getUserId())
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ".concat("AccessToken"))
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(
                        MockMvcRestDocumentationWrapper.document("회원 탈퇴 예제",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("회원 탈퇴")
                                                .pathParameters(
                                                        parameterWithName("user-id").description("회원 식별자")
                                                )
                                                .requestHeaders(
                                                        headerWithName(HttpHeaders.AUTHORIZATION).description("발급 받은 인증 토큰")
                                                )
                                                .build()
                                )
                        )
                );
    }



    private  QuestionDto.MyPageQuestionResponse getResponse() {
        return QuestionDto.MyPageQuestionResponse.builder()
                .questionId(1L)
                .title("제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다")
                .content("내용입니다. 내용입니다.")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    private AnswerDto.MyPageAnswerResponse getAnswer() {
        return AnswerDto.MyPageAnswerResponse.builder()
                .answerId(1L)
                .content("내용입니다. 내용입니다 내용입니다. 내용입니다")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }
}