package com.rainbow.sof.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/api/v1/**")
                //TODO: React 기본 포트가 3000번이라 우선 적용함. 추후 변경 가능성 있음
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PATCH","DELETE");
    }
}
