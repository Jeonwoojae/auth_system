package com.example.monolithic_auth;

import com.example.monolithic_auth.filter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/api/signin",
                        "/login");
    }
}
