package com.example.demo.config;

import com.example.demo.Interceptor.AuthenticationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .excludePathPatterns("/upload/**")
                .addPathPatterns("/**"); // 拦截所有请求，通过判断是否有 @LoginRequired 注解
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
