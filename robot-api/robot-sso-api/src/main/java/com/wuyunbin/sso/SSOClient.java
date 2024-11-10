package com.wuyunbin.sso;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(
        name = "robot-sso-service",
        contextId = "robot-sso-service"
)
public interface SSOClient {

    @GetMapping("memberApi/getMemberIdByToken/{token}")
    String getMemberIdByToken(@PathVariable("token") String token);


}
