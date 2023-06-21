//package com.rainbow.sof.domain.user.auth;
//
//import com.epages.restdocs.apispec.ResourceSnippetParameters;
//import com.google.gson.Gson;
//import com.rainbow.sof.domain.user.auth.handler.WithAuthUser;
//import com.rainbow.sof.domain.user.auth.util.userDetail.UsersDetail;
//import com.rainbow.sof.domain.user.auth.util.userDetailService.UsersDetailService;
//import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
//import com.rainbow.sof.domain.user.entity.User;
//import com.rainbow.sof.domain.user.mapper.UserMapper;
//import com.rainbow.sof.domain.user.repository.UserRepository;
//import com.rainbow.sof.domain.user.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import javax.annotation.PostConstruct;
//import javax.validation.constraints.Positive;
//import java.util.Optional;
//
//import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs
//public class UserLoginTest {
//
//    private final String User_ORIGIN_URI ="/api/v1";
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    private final Gson gson = new Gson();
//
//    @Autowired
//    private UserRepository repository;
//
//    private String userContext;
//
//    private User testUserEntity;
//
//    private UsersDetail usersDetail;
//
//    @MockBean
//    private UsersDetailService userDetailsService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    //   private Optional<User> optionalUser;
////
//   @PostConstruct
//   public void setUserDetails(){
//       User user = User.builder()
//               .name("admin")
//               .email("test@test.com")
//               .password("q12341234")
//               .build();
//       repository.save(user);
//   }
//
//
//    @BeforeEach
//    public void init(){
//        this.userContext = gson.toJson(
//                UserDto.CreationLoginDto.builder()
//                        .username("admin")
//                        .password("q12341234")
//                        .build()
//        );
//        User user = User.builder()
//                .userId(1L)
//                .email("test@test.com")
//                .name("admin")
//                .password("q12341234")
//                .build();
//        usersDetail = new UsersDetail(user);
//        repository.save(user);
//
//
//    }
//
//    @Test
//    @WithAuthUser(email = "test@test.com")
//    @DisplayName("로그인 테스트")
//    public void createUserTest() throws Exception{
//       given(userDetailsService.loadUserByUsername(Mockito.anyString())).willReturn(usersDetail);
//       given(passwordEncoder.encode(Mockito.anyString())).willReturn(usersDetail.getPassword());
//
//        //MockMvcRequestBuilders 대신 RestDocumentationRequestBuilders 를 사용해야 문서화가 됨
//        mockMvc.perform(RestDocumentationRequestBuilders.post(User_ORIGIN_URI+"/login")
//                        .header(HttpHeaders.AUTHORIZATION, "\u200BBearer {ACCESS_TOKEN}")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userContext))
//
//                .andExpect(MockMvcResultMatchers.status().isOk())
//
//
//                .andDo(
//                        document("회원 로그인 예제",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                resource(
//                                        ResourceSnippetParameters.builder()
//                                                .description("회원 로그인")
//                                                .requestFields(
//                                                        fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름"),
//                                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
//                                                )
//                                                .requestHeaders(
//                                                        headerWithName(HttpHeaders.AUTHORIZATION).description("발급 받은 인증 토큰")
//                                                )
//                                                .responseFields(
//                                                        fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 식별 Id")
//                                                )
//                                                .build()
//                                )
//                        )
//                );
//    }
//}
