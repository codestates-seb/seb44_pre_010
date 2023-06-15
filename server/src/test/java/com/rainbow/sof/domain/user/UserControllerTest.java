package com.rainbow.sof.domain.user;


import com.google.gson.Gson;
import com.rainbow.sof.user.dto.singleDto.UserDto;
import com.rainbow.sof.user.entity.User;
import com.rainbow.sof.user.mapper.UserMapper;
import com.rainbow.sof.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private final String User_ORIGIN_URI ="/api/v1";

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
        this.testUserEntity = User.builder()
                .userId(1L)
                .name("홍길동")
                .email("test@test.com")
                .password("12341234")
                .status(User.Status.USER_ACTIVE)
                .build();
        this.userContext = gson.toJson(UserDto.SignUpPost.builder()
                .name("홍길동").
                password("12341234").
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

}
