package com.konkukrent.demo.auth;

import com.konkukrent.demo.auth.SessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**") // 모든 경로에 대해 인터셉터 적용"
                .excludePathPatterns("/users/login", "/users/logout", "/users/signup", "/swagger-ui/**", "/v3/api-docs/**"); // 로그인, 회원가입 경로는 제외
    }
}