package com.wuyunbin.sso.controller;

import com.wuyunbin.sso.common.Result;
import com.wuyunbin.sso.dto.LoginDTO;
//import com.wuyunbin.sso.dto.WeChatLoginDTO;
import com.wuyunbin.sso.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @GetMapping("getCode/{phone}")
    public Result<Object> getCode(@PathVariable String phone){
        log.info("phone:{}",phone);
        //memberService.sendCode(phone);
        return Result.success();
    }


}
