package com.wuyunbin.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.mapper.MemberMapper;
import com.wuyunbin.sso.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("memberServiceImplByCode")
public class MemberServiceImplByCode extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


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
        //获取redis中的验证码
        String s = redisTemplate.opsForValue().get(key);
        log.info("redis中获取的验证码是：{}", s);

        log.info("验证码是：{}", code);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        log.info("短信验证码方式...");
        Member member = this.lambdaQuery()
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
            this.save(member);
        }

        //签发token

        return "token:oj8k";
    }
}
