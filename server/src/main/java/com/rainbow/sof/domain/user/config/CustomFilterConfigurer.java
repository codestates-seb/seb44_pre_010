package com.rainbow.sof.domain.user.config;

import com.rainbow.sof.domain.user.auth.filter.JwtAuthenticationFilter;
import com.rainbow.sof.domain.user.auth.filter.JwtVerificationFilterV2;
import com.rainbow.sof.domain.user.auth.filter.VerificationFilter;
import com.rainbow.sof.domain.user.auth.handler.UserAuthenticationFailureHandler;
import com.rainbow.sof.domain.user.auth.handler.UserAuthenticationSuccessHandler;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtTokenizer jwtTokenizer;



    public CustomFilterConfigurer(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager,jwtTokenizer);

        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

        JwtVerificationFilterV2 jwtVerificationFilterV2 = new JwtVerificationFilterV2(jwtTokenizer);

        builder.addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtVerificationFilterV2,JwtAuthenticationFilter.class);
    }
}
