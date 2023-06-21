package com.rainbow.sof.global.config;

import com.rainbow.sof.global.handler.AuthenticationArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationArgumentResolver authenticationArgumentResolver;
    /*@Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/api/v1/**")
                //TODO: React 기본 포트가 3000번이라 우선 적용함. 추후 변경 가능성 있음
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PATCH","DELETE");
    }*/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(authenticationArgumentResolver);
    }
}