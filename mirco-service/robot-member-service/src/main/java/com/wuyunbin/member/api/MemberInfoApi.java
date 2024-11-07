package com.wuyunbin.member.api;

import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.member.service.MemberInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyunbin
 */
@Slf4j
@RestController
@RequestMapping("/memberInfoApi")
public class MemberInfoApi {

    @Resource
    private MemberInfoService memberInfoService;

    @GetMapping("{id}")
    public MemberInfo getInfoById(@PathVariable String id){
        return this.memberInfoService.getById(id);
    }
}
