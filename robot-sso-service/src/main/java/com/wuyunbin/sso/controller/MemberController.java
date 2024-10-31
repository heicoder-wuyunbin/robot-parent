package com.wuyunbin.sso.controller;

import com.wuyunbin.sso.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.WeChatLoginDTO;
import com.wuyunbin.sso.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @PostMapping("login")
    public Result<HashMap<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}", loginDTO);
        String token = memberService.login(loginDTO);
        HashMap<String, Object> map=new HashMap<>();
        return Result.success(map);
    }

    @PostMapping("loginByWeChat")
    public Result<String> loginByWeChat(@RequestBody WeChatLoginDTO weChatLoginDTO) {
        log.info("{}", weChatLoginDTO);
        String token = memberService.loginByWeChat(weChatLoginDTO);
        return Result.success(token);
    }

    @GetMapping("getCode/{phone}")
    public Result<Object> getCode(@PathVariable String phone){
        log.info("phone:{}",phone);
        memberService.sendCode(phone);
        return Result.success();
    }

    @PostMapping("login/phone")
    public void loginByPhone(@RequestBody LoginDTO loginDTO){
        log.info("loginDTO:{}",loginDTO);
        memberService.login(loginDTO);
    }
}
