package com.wuyunbin.member.api;

import com.wuyunbin.common.Result;
import com.wuyunbin.member.entity.MemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wuyunbin
 */
@Component
@FeignClient(
        name = "robot-member-service",
        contextId = "robot-member-service"
)
public interface MemberInfoClient {
    @PostMapping("memberInfo")
    Result<Object> save(@RequestBody MemberInfo memberInfo);

    @GetMapping("memberInfoApi/{id}")
    MemberInfo getInfoById(@PathVariable("id") String id);
}
