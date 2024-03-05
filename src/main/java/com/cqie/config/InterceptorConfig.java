package com.cqie.config;

import com.cqie.interceptor.TokenInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.cqie.controller")
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> urls = new ArrayList<>();
        urls.add("/doc.html");
        urls.add("/accountInfo/login");
        urls.add("/accountInfo/666");
        urls.add("/swagger-resources/**");
        urls.add("/v2/**");
        urls.add("/webjars/**");
        urls.add("/error");
        urls.add("favicon.ico");
        urls.add("**/swagger-ui.html/**");
        urls.add("swagger-ui.html/**");

        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(urls);
    }

}