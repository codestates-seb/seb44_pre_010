package com.rainbow.sof.domain.user.auth.filter;

import com.rainbow.sof.domain.user.auth.filter.handler.UserAuthenticationSuccessHandler;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


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

        builder.addFilter(jwtAuthenticationFilter);
    }
}
