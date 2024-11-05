package com.wuyunbin.member.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.member.service.MemberInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-11-05
 */
@Slf4j
@RestController
@RequestMapping("/memberInfo")
public class MemberInfoController {

    @Resource
    private MemberInfoService memberInfoService;

    @PostMapping("")
    public Result<Object> save(@RequestBody MemberInfo memberInfo){
        log.info("memberInfo:{}",memberInfo);
        //memberInfoService.save(memberInfo);
        return Result.success();
    }

}
