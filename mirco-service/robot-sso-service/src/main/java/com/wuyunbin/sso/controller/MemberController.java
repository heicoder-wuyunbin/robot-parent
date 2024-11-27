package com.wuyunbin.sso.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.SendSmsDTO;
import com.wuyunbin.sso.enums.AuthType;
import com.wuyunbin.sso.service.MemberAuthService;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.vo.MemberLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
@Tag(name = "会员管理")
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

    @Operation(summary = "会员登录")
    @PostMapping("login")
    public Result<MemberLoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}", loginDTO);

        MemberAuthService memberService = memberAuthServiceHashMap.get(loginDTO.getType());
        MemberLoginVO memberLoginVO = memberService.login(loginDTO);

        return Result.success(memberLoginVO);
    }

    @Operation(summary = "获取验证码")
    @PostMapping("getCode")
    public Result<Object> getCode(@RequestBody SendSmsDTO sendSmsDTO){

        MemberAuthService memberAuthService = memberAuthServiceHashMap.get(AuthType.VERIFICATION_CODE.getClassName());
        memberAuthService.sendCode(sendSmsDTO.getPhone());
        //todo 记录场景使用次数
        return Result.success();
    }

    @Operation(summary = "根据token获取会员信息")
    @GetMapping("findByToken")
    public Result<MemberInfo> findByToken(){
        MemberInfo memberInfo =memberService.findByToken();
        return Result.success(memberInfo);
    }

    @Operation(summary = "根据token获取会员id")
    @GetMapping("getMemberIdByToken")
    public Result<String> getMemberIdByToken(){
        log.info("1");
        return Result.success(memberService.getMemberIdByToken()) ;
    }
}
