package com.wuyunbin.sso.controller;

import com.wuyunbin.sso.common.Result;
import com.wuyunbin.sso.dto.WeChatLoginDTO;
import com.wuyunbin.sso.service.MemberService;
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
 * @since 2024-10-27
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("loginByWeChat")
    public Result<String> loginByWeChat(@RequestBody WeChatLoginDTO weChatLoginDTO) {
        log.info("{}", weChatLoginDTO);
        String token = memberService.loginByWeChat(weChatLoginDTO);
        return Result.success(token);
    }
}
