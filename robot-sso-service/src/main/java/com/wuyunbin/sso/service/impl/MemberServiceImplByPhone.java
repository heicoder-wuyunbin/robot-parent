package com.wuyunbin.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.mapper.MemberMapper;
import com.wuyunbin.sso.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("memberServiceImplByPhone")
public class MemberServiceImplByPhone extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Override
    public void sendCode(String phone) {
        throw new RuntimeException("手机号加密码方式不需要发送验证码");
    }

    @Override
    public String login(LoginDTO loginDTO) {

        log.info("账号密码登录方式....");
        Member member = this.lambdaQuery()
                .eq(Member::getPhone, loginDTO.getPhone())
                .one();
        if (member == null) {
            throw new RuntimeException("未注册的用户或者密码不正确");
        }

        if (!member.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("未注册的用户或者密码不正确");
        }
        return "token:oj8k";

    }
}
