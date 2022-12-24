package com.example.monolithic_auth;

import com.example.monolithic_auth.filter.AdminCheckInterceptor;
import com.example.monolithic_auth.filter.LoginCheckInterceptor;
import com.example.monolithic_auth.utils.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private JwtProvider jwtProvider = new JwtProvider();
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor(jwtProvider))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/signin",
                        "/login");// 로그인과 유저권한 확인
        registry.addInterceptor(new AdminCheckInterceptor(jwtProvider))
                .order(2)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/user/signin",
                        "/login",
                        "/user/{id}");// 관리자 권한 확인
    }
}
