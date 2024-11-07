package com.wuyunbin.sso.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.SendSmsDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.enums.AuthType;
import com.wuyunbin.sso.service.MemberAuthService;
import com.wuyunbin.sso.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    private final Map<String,MemberAuthService> memberAuthServiceHashMap;

    public MemberController(Map<String, MemberAuthService> memberAuthServiceHashMap){
        this.memberAuthServiceHashMap =memberAuthServiceHashMap;
    }

    @PostMapping("login")
    public Result<HashMap<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}", loginDTO);

        MemberAuthService memberService = memberAuthServiceHashMap.get(loginDTO.getType());
        String token =memberService.login(loginDTO);

        HashMap<String, Object> map=new HashMap<>();
        map.put("token",token);
        return Result.success(map);
    }

    @PostMapping("getCode")
    public Result<Object> getCode(@RequestBody SendSmsDTO sendSmsDTO){

        MemberAuthService memberAuthService = memberAuthServiceHashMap.get(AuthType.VERIFICATION_CODE.getClassName());
        memberAuthService.sendCode(sendSmsDTO.getPhone());
        //todo 记录场景使用次数
        return Result.success();
    }

    @GetMapping("findByToken")
    public Result<MemberInfo> findByToken(){
        MemberInfo memberInfo =memberService.findByToken();
        return Result.success(memberInfo);
    }
}
