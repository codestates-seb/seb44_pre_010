package com.rainbow.sof.domain.user.config;
import com.rainbow.sof.domain.user.auth.handler.authError.UserAuthenticationEntryPoint;
import com.rainbow.sof.domain.user.auth.jwt.JwtTokenizer;
import com.rainbow.sof.domain.user.config.CustomFilterConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static com.rainbow.sof.domain.user.util.CustomEnumUri.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final static String USER_DETAIL_URL="/api/v1/users";

    public SecurityConfiguration(JwtTokenizer jwtTokenizer) {
        this.jwtTokenizer = jwtTokenizer;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .and()
                .apply(customFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .antMatchers(HttpMethod.PATCH,"/api/v1/questions/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST,"/api/v1/questions/**").hasRole("USER")
                        .antMatchers(USER_DETAIL_URL+"/**").authenticated()// /api/v1/users 의 하위 경로는 인증되야지만 접근가능하다
                        .antMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().permitAll());
        return http.build();
    }

    @Bean
    public CustomFilterConfigurer customFilterConfigurer(){
        return new CustomFilterConfigurer(jwtTokenizer);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");

//        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(
                List.of("Authorization","X-AUTH-TOKEN")
        );

        configuration.setExposedHeaders(
                Arrays.asList("Content-Type","X-AUTH-TOKEN","Authorization")
        );

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
