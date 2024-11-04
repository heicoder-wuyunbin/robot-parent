package com.wuyunbin.sso.service.impl.member;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyunbin.sso.bo.OpenIdBO;
import com.wuyunbin.sso.config.WeChatConfig;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.service.MemberAuthService;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.utils.JwtUtil;
import com.wuyunbin.sso.wechat.WeChatAPI;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author wuyunbin
 */
@Slf4j
@Service("weChatAuthService")
public class WeChatAuthServiceImpl implements MemberAuthService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private WeChatConfig weChatConfig;

    @Resource
    private MemberService memberService;


    @Override
    public void sendCode(String phone) {
        throw new RuntimeException("微信登录不需要发送验证码");
    }

    @Override
    public String login(LoginDTO loginDTO) {
        String url = WeChatAPI.jscode2session;
        url = url.replace("{APPID}", weChatConfig.getAppid());
        url = url.replace("{SECRET}", weChatConfig.getSecret());
        url = url.replace("{CODE}", loginDTO.getCode());

        String str = restTemplate.getForObject(url, String.class);
        OpenIdBO bo = JSON.parseObject(str, OpenIdBO.class);
        log.info("{}", bo);

        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getOpenId, bo.getOpenid());
        Member member = memberService.getOne(wrapper);

        //登录即注册
        if (member == null) {
            //预防空指针
            member = new Member();
            member.setOpenId(bo.getOpenid());
            memberService.save(member);
        }

        //签发token
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",member.getId());
        return jwtUtil.createToken(map);
    }
}
