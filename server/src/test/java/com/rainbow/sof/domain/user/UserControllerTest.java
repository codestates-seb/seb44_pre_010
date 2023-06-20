package com.rainbow.sof.domain.user;


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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private final String User_ORIGIN_URI ="/api/v1";
    private final String User_USERDATA_ORIGIN_URI ="/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private UserService service;

    @Mock
    private UserMapper mapper;

    private String userContext;

    private User testUserEntity;

    @BeforeEach
    public void initTest(){
        List<Question> questionList =new ArrayList<>();
        questionList.add(Question.builder().questionId(1L).build());
        List<Answer> answerList =new ArrayList<>();
        answerList.add(Answer.builder().answerId(1L).build());
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

        mockMvc.perform(MockMvcRequestBuilders.post(User_ORIGIN_URI+"/signup")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userContext))

                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser
    @DisplayName("회원 정보 확인 테스트")
    public void getUserTest() throws Exception{
        QuestionDto.Response response = getResponse();
        AnswerDto.Response answerResponse= getAnswer();
        List<QuestionDto.Response> list = new ArrayList<>();
        List<AnswerDto.Response> answerDtoList = new ArrayList<>();
        list.add(response);
        answerDtoList.add(answerResponse);

        MyPageResponseDto responseDto =MyPageResponseDto.builder()
                .name("홍길동").questionList(list).AnswerList(answerDtoList)
                .build();

        String context = gson.toJson(UserDataResponse.builder()
                .data(responseDto));

        given(service.checkToFindByUserFromEmail(Mockito.anyString(),Mockito.anyLong())).willReturn(testUserEntity);
        given(service.findVerifiedUser(Mockito.anyLong())).willReturn(testUserEntity);
        given(mapper.userToMyPageDto(testUserEntity)).willReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(context))

                .andExpect(MockMvcResultMatchers.status().isOk());

    }



    @Test
    @WithMockUser
    @DisplayName("회원 탈퇴 테스트")
    @Disabled("빌드 테이스위해서 잠시 ignore")// Autor:윤다영, 빌드 테스트 위해서 Ignore
    public void deleteUserTest() throws Exception{
        doNothing().when(service).deleteUser(Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete(User_ORIGIN_URI+"/users/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }



    private  QuestionDto.Response getResponse() {
        return QuestionDto.Response.builder()
                .questionId(1L)
                .title("제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다")
                .content("내용입니다. 내용입니다.")
                .build();
    }

    private AnswerDto.Response getAnswer() {
        return AnswerDto.Response.builder()
                .answerId(1L)
                .content("내용입니다. 내용입니다 내용입니다. 내용입니다")
                .build();
    }
}
