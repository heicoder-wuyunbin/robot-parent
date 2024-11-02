package com.wuyunbin.sso.controller;

import com.wuyunbin.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.dto.SendSmsDTO;
import com.wuyunbin.sso.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MemberController(){
        System.out.println("controller...");
    }

    /*
    泛型中String位置（key位置)实际就是service实现类的对象名字
     */
    @Autowired
    private Map<String,MemberService> memberServiceHashMap;

    @PostMapping("login")
    public Result<HashMap<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}", loginDTO);

        MemberService memberService = memberServiceHashMap.get(loginDTO.getType());
        String token =memberService.login(loginDTO);

        HashMap<String, Object> map=new HashMap<>();
        map.put("token",token);
        return Result.success(map);
    }

    @PostMapping("getCode")
    public Result<Object> getCode(@RequestBody SendSmsDTO sendSmsDTO){

        MemberService memberService = memberServiceHashMap.get("memberServiceImplByCode");
        memberService.sendCode(sendSmsDTO.getPhone());
        //todo 记录场景使用次数
        return Result.success();
    }


}
