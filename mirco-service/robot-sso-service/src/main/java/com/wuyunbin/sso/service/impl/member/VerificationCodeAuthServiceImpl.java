package com.wuyunbin.sso.service.impl.member;

import com.alibaba.fastjson2.JSON;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.service.MemberAuthService;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wuyunbin
 */
@Slf4j
@Service("verificationCodeAuthService")
public class VerificationCodeAuthServiceImpl implements MemberAuthService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MemberService memberService;

    @Override
    public void sendCode(String phone) {
        Random random = new Random();
        String code = random.nextInt(8888) + 1000 + "";

        /*
        todo 缓存验证码
        redis编排方式： 项目名:模块名:场景名:特征码
         */
        String key = "robot:sso:login:" + phone;
        redisTemplate.opsForValue().set(key, code, 360, TimeUnit.SECONDS);
        //往MQ中发送短信
        rabbitTemplate.convertAndSend("test.direct","sms","您的登录短信验证码："+code);
        log.info("验证码是：{}", code);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        log.info("短信验证码方式...");
        Member member = memberService.lambdaQuery()
                .eq(Member::getPhone, loginDTO.getPhone())
                .one();

        //redis取值对比
        String key = "robot:sso:login:" + loginDTO.getPhone();
        String codeInRedis = redisTemplate.opsForValue().get(key);
        if(codeInRedis==null){
            throw new RuntimeException("短信验证码丢失或者失效");
        }
        if (!codeInRedis.equals(loginDTO.getCode())) {
            throw new RuntimeException("验证码错误");
        }

        if(member==null){
            //新用户
            member=new Member();
            member.setPhone(loginDTO.getPhone());
            //存入新用户
            memberService.save(member);
            //推入队列
            HashMap<String,Object> map=new HashMap<>();
            map.put("id",member.getId());
            map.put("phone",member.getPhone());
            String str = JSON.toJSONString(map);
            log.info("json字符串:{}",str);
            rabbitTemplate.convertAndSend("info",str);
        }

        //签发token
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",member.getId());
        String token = jwtUtil.createToken(map);
        return token;
    }
}
