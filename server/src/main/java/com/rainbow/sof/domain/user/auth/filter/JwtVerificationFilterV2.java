package com.rainbow.sof.domain.user.auth.filter;

import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilterV2 extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;

    public JwtVerificationFilterV2(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String checkJwtInHeader = request.getHeader("Authorization");//헤더에서 토큰을 가져옴
//        if (checkJwtInHeader == null || !checkJwtInHeader.startsWith("Bearer")){
//            request.setAttribute("exception", HttpStatus.UNAUTHORIZED);
//        }
        //가져온 토큰이 없거나 올바른 형태의 토큰이 아니라면 false 를 반환
        return checkJwtInHeader == null || !checkJwtInHeader.startsWith("Bearer");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String getToken = request.getHeader("Authorization").replace("Bearer ",""); // 헤더에서 토큰을 가져옴
        String encodeKey = jwtTokenizer.secretKeyEncodeBase64(jwtTokenizer.getSecretKeySting());

        try {
            //이때 복호화가 올바르게 되지 않는다면 이 토큰은 잘못된 토큰임, 즉 인증되지 않음
            Map<String, Object> claims = jwtTokenizer.getClaims(getToken, encodeKey).getBody(); // 토큰을 복호화 하여 자격을 가져옴
            List<GrantedAuthority> authorities = getAuthorities(claims);

            createUsernamePasswordAuthenticationToken(claims, authorities);
        }
        catch (BusinessLogicException re){
            request.setAttribute("exception", re);
        }
        catch (SignatureException se){
            request.setAttribute("exception", se);
        }
        catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        }
        catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request,response);

    }

    private static void createUsernamePasswordAuthenticationToken(Map<String, Object> claims, List<GrantedAuthority> authorities) {
        //claims 에서 정보를 가져오는데 성공했다면 토큰은 인증되었다.
        //인증된 토큰이기떄문에 인증된 UsernamePasswordAuthenticationToken 를 만든다.
        //이후 SecurityContextHolder 에 저장하여 getPrincipal 에 접근이 가능하도록 한다.
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims.get("email"),null, authorities);//AuthorityUtils.createAuthorityList( "ROLE_USER")
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private List<GrantedAuthority> getAuthorities(Map<String, Object> claims) {
        return jwtTokenizer.getADMIN_SUBJECT().equals(claims.get("email"))
                ? AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ADMIN", "ROLE_USER", "USER") :
                AuthorityUtils.createAuthorityList("ROLE_USER", "USER");
    }
}
