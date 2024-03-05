package com.cqie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 创建CorsConfiguration对象，并进行跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");  // 允许所有来源跨域访问，可以根据需要设置特定的来源
        corsConfiguration.addAllowedHeader("*");  // 允许所有请求头跨域访问，可以根据需要设置特定的请求头
        corsConfiguration.addAllowedMethod("*");  // 允许所有请求方法跨域访问，可以根据需要设置特定的请求方法
        
        // 创建UrlBasedCorsConfigurationSource对象，并为指定的路径添加CorsConfiguration配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);  // 对所有路径进行跨域配置
        
        // 创建CorsFilter对象，并返回
        return new CorsFilter(source);
    }
}