package com.rainbow.sof.domain.user.auth.filter;

import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import io.swagger.v3.core.util.AnnotationsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;


public class VerificationFilter extends GenericFilterBean {
    private final JwtTokenizer jwtTokenizer;

    public VerificationFilter(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String getToken = checkInToken(httpRequest); // 헤더에서 토큰을 가져옴
        String encodeKey = jwtTokenizer.secretKeyEncodeBase64(jwtTokenizer.getSecretKeySting());

        Map<String,Object> claims = jwtTokenizer.getClaims(getToken,encodeKey).getBody(); // 토큰을 복호화 하여 자격을 가져옴
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims.get("email"),null,null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(httpRequest,response);
    }

    private String checkInToken(HttpServletRequest httpRequest) {
        String getHeaderToken = httpRequest.getHeader("Authorization");
        if (getHeaderToken == null || !getHeaderToken.startsWith("Bearer")){
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }
        return getHeaderToken.replace("Bearer ","");
    }
}
