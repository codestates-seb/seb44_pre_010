package com.rainbow.sof.domain.user.auth.handler.oauthHandler;

import com.rainbow.sof.domain.user.auth.jwt.DelegateTokenService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
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

    public OAuth2SuccessHandler(DelegateTokenService delegateTokenService) {
        this.delegateTokenService = delegateTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User userData = (User) authentication.getPrincipal();
        log.info("onAuthenticationSuccess userName: {}", userData.getName());
        log.info("onAuthenticationSuccess: userEmail:  {}", userData.getEmail());
        redirect(request, response,userData);

    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        String accessToken = "Bearer "+delegateTokenService.delegateAccessToken(user);
        String refreshToken = delegateTokenService.delegateRefreshToken(user);
        String redirectURL = createURI(accessToken,refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response,redirectURL);
    }

    //TODO: 클라이언트 주소로 변경 필요
    private URI createURI(String accessToken,String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();// 순서 맞게 보내기위해 LinkedMultiValueMap 사용
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("ec2-52-78-15-107.ap-northeast-2.compute.amazonaws.com")
                .path("/oAuht")
                .queryParams(queryParams)
                .build()
                .toUri();

        // 로컬 클라이언트 테스트 URI
//        return UriComponentsBuilder
//                .newInstance()
//                .scheme("http")
//                .host("localhost")
//                .port(3000)
//                .path("/oAuht")
//                .queryParams(queryParams)
//                .build()
//                .toUri();
    }


}