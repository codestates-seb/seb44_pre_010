package com.rainbow.sof.domain.user.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.sof.domain.user.auth.jwt.DelegateTokenService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer tokenizer;
    public final DelegateTokenService delegateTokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer tokenizer, DelegateTokenService delegateTokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenizer = tokenizer;
        this.delegateTokenService = delegateTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            UserDto.CreationLoginDto creationLoginDto = objectMapper.readValue(request.getInputStream(), UserDto.CreationLoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(creationLoginDto.getUsername(),creationLoginDto.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException("읽어올 수 없는 사용자 정보 입니다.");
        }



    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User)authResult.getPrincipal();

        String accessToken  = delegateTokenService.delegateAccessToken(user);
        String refreshToken = delegateTokenService.delegateRefreshToken(user);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh",refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request,response,authResult);

    }

    //TODO: 이전 토큰 생성 메서드
//    private String delegateAccessToken(User user){
//        Map<String,Object> claims = new HashMap<>();
//        claims.put("email",user.getEmail());
//
//        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());
//        String base64EncodedSecretKeyString = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeySting());
//        String subject=user.getEmail();
//
//        return tokenizer.generateAccessToken(claims,subject,expiration,base64EncodedSecretKeyString);
//
//    }
//
//    private String delegateRefreshToken(User user){
//
//        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());
//        String base64EncodedSecretKeyString = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeySting());
//        String subject=user.getEmail();
//
//        return tokenizer.generateRefreshToken(subject,expiration,base64EncodedSecretKeyString);
//
//    }
}
