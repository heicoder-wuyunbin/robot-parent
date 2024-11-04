package com.wuyunbin.sso.service.impl.member;

import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.service.MemberAuthService;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author wuyunbin
 */
@Slf4j
@Service("passwordAuthService")
public class PasswordAuthServiceImpl implements MemberAuthService {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MemberService memberService;

    @Override
    public void sendCode(String phone) {
        throw new RuntimeException("手机号加密码方式不需要发送验证码");
    }

    @Override
    public String login(LoginDTO loginDTO) {

        log.info("账号密码登录方式....");
        Member member = memberService.lambdaQuery()
                .eq(Member::getPhone, loginDTO.getPhone())
                .one();
        if (member == null) {
            throw new RuntimeException("未注册的用户或者密码不正确");
        }

        if (!member.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("未注册的用户或者密码不正确");
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",member.getId());
        return jwtUtil.createToken(map);

    }
}
