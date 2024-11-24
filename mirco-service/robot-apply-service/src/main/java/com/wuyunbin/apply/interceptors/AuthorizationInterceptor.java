package com.wuyunbin.apply.interceptors;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

//    public AuthorizationInterceptor(){
//        System.out.println("========AuthorizationInterceptor=======");
//        System.out.println(this);
//        System.out.println("========AuthorizationInterceptor=======");
//    }




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截器生效了...");

        return true;
    }
}
