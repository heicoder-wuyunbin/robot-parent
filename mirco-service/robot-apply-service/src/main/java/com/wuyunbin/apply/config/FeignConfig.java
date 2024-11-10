package com.wuyunbin.apply.config;

import feign.RequestInterceptor;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuyunbin
 */
@Slf4j
@Configuration
public class FeignConfig {
    @Resource
    private HttpServletRequest request;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String uri = request.getRequestURI();
            String token = request.getHeader("Authorization");
            log.info("uri:{}", uri);
            log.info("token:{}", token);
            //todo 存疑？
            requestTemplate.header("uri", uri);
            requestTemplate.header("Authorization", token);
        };
    }
}