package com.wuyunbin.apply.config;

import com.wuyunbin.apply.interceptors.AuthorizationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        //采用new对象的形式来注册拦截器对象会脱离SpringBoot管理，DI就失效了（不推荐）
//        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
//        System.out.println("========WebConfig=======");
//        System.out.println(this);
//        System.out.println("========WebConfig=======");
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**");
    }
}
