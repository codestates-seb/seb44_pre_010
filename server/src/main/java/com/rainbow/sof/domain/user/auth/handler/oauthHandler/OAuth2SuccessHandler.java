package com.rainbow.sof.domain.user.auth.handler.oauthHandler;

import com.rainbow.sof.domain.user.auth.jwt.DelegateTokenService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.rainbow.sof.domain.user.util.CustomEnumUri.*;

@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final DelegateTokenService delegateTokenService;
    private final UserService userService;

    public OAuth2SuccessHandler(DelegateTokenService delegateTokenService, UserService userService) {
        this.delegateTokenService = delegateTokenService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User UserData = (OAuth2User)authentication.getPrincipal();
        String email = String.valueOf(UserData.getAttributes().get("email"));
        String name = String.valueOf(UserData.getAttributes().get("name"));

        User createUser = User.builder().email(email)
                .name(name)
                        .password("Q1234123452234522").build();
        User oAuth2User= saveUser(createUser);
        log.info("onAuthenticationSuccess: {} ", oAuth2User.getName());
        log.info("onAuthenticationSuccess: {}" ,oAuth2User.getEmail());
        redirect(request, response,oAuth2User);

    }

    private User saveUser(User oAuth2User) {
        try {
            return userService.createUser(oAuth2User);
        }catch (BusinessLogicException e){

            return e.getExceptionCode().equals(ExceptionCode.USER_EXISTS) ?
                    userService.findByUserFromEmail(oAuth2User.getEmail()) :
                    null;
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        String accessToken = delegateTokenService.delegateAccessToken(user);
        String refreshToken = delegateTokenService.delegateRefreshToken(user);


        String uri = createURI().toString();
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh",refreshToken);

        getRedirectStrategy().sendRedirect(request, response, "/mypage");
        String s = getRedirectStrategy().toString();
    }

    //TODO: 클라이언트 주소로 변경 필요
    private URI createURI() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
//                .port(80)
                .path("/receive-token.html")
                .build()
                .toUri();
    }


}
//    Map<String, String>  oAuth2UserData = new HashMap<>();
//        oAuth2UserData.put("email", String.valueOf(oAuth2User.getAttributes().get("email")));
//        oAuth2UserData.put("name", String.valueOf(oAuth2User.getAttributes().get("name")));
//        User createUser= saveUser(oAuth2UserData);