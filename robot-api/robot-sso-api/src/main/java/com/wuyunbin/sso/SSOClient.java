package com.wuyunbin.sso;


import com.wuyunbin.sso.entity.Merchant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "robot-sso-service",
        contextId = "robot-sso-service"
)
public interface SSOClient {

    @GetMapping("memberApi/getMemberIdByToken")
    String getMemberIdByToken();

    @PostMapping("merchantApi")
    Boolean save(@RequestBody Merchant merchant);
}
