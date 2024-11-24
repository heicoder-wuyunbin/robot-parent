package com.wuyunbin.apply.interceptors;

import com.wuyunbin.apply.context.BaseContext;
import com.wuyunbin.sso.SSOClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Lazy
    @Resource
    private SSOClient ssoClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);
        String memberId = ssoClient.getMemberIdByToken();
        log.info("memberId:{}", memberId);
        if (memberId != null) {
            BaseContext.set(memberId);
        }
        return true;
    }
}
