package com.rainbow.sof.domain.user.auth.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rainbow.sof.domain.user.controller.UserController;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.util.CustomEnumUri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        User user = setUserInfo(response, authentication);
        printUserLog(user);
    }

    private static User setUserInfo(HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        httpResponseCreation(response, user);
        return user;
    }

    private static void httpResponseCreation(HttpServletResponse response, User user) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.setHeader("Location", CustomEnumUri.USER_DEFAULT_URL.getUri()+user.getUserId());
        UserDto.LoginResponse loginResponse = UserDto.LoginResponse.builder()
                .userId(user.getUserId())
                .build();
        //TODO: 시큐리티 수정 중
//        response.getWriter().write(gson.toJson(loginResponse,UserDto.LoginResponse.class));

    }

    private static void printUserLog(User user) {
        log.info("# Authenticated successfully!");
        log.info("# 로그인 정보 이메일: "+ user.getEmail());
        log.info("# 로그인 정보 유저 ID: "+ user.getEmail());
    }
}
