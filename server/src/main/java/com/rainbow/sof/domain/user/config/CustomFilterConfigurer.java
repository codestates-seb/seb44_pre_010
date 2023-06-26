package com.rainbow.sof.domain.user.config;

import com.rainbow.sof.domain.user.auth.filter.JwtAuthenticationFilter;
import com.rainbow.sof.domain.user.auth.filter.JwtVerificationFilterV2;
import com.rainbow.sof.domain.user.auth.handler.loginhandle.UserAuthenticationFailureHandler;
import com.rainbow.sof.domain.user.auth.handler.loginhandle.UserAuthenticationSuccessHandler;
import com.rainbow.sof.domain.user.auth.handler.oauthHandler.OAuth2SuccessHandler;
import com.rainbow.sof.domain.user.auth.jwt.DelegateTokenService;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtTokenizer jwtTokenizer;
    public final DelegateTokenService delegateTokenService;




    public CustomFilterConfigurer(JwtTokenizer jwtTokenizer, DelegateTokenService delegateTokenService) {
        this.jwtTokenizer = jwtTokenizer;
        this.delegateTokenService = delegateTokenService;

    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager,jwtTokenizer,delegateTokenService);


        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

        JwtVerificationFilterV2 jwtVerificationFilterV2 = new JwtVerificationFilterV2(jwtTokenizer);

        builder.addFilterAfter(jwtVerificationFilterV2, OAuth2LoginAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter,JwtVerificationFilterV2.class);
    }

//.addFilter(jwtAuthenticationFilter)
//                .addFilterAfter(jwtVerificationFilterV2,JwtAuthenticationFilter.class);

}
