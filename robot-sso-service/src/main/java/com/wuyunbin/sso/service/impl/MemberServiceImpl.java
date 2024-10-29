package com.wuyunbin.sso.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.sso.bo.OpenIdBO;
import com.wuyunbin.sso.bo.WeChatAccessToken;
import com.wuyunbin.sso.config.WeChatConfig;
import com.wuyunbin.sso.dto.WeChatLoginDTO;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.mapper.MemberMapper;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.wechat.WeChatAPI;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Resource
    private WeChatConfig weChatConfig;

    /*
    https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx084d5d60b374c2a4&secret=f5207f4068251d589cc12923acd2d759
    {
	"access_token": "85_6q4441_UKGmXS6vcAKlC1hzEzh51EVxw1qFIR6m3oZVnL7bmOgeYZityrwQZ5UCfe9SOp41ZylPdkfs-_mwQh2hNeRx5bU5RGZd1aTsq7ttQxw7BRYlZbS5gjmgZBJeADAUWE",
	"expires_in": 7200
}
     */
    @Override
    public String loginByWeChat(WeChatLoginDTO weChatLoginDTO) {
       String url= WeChatAPI.jscode2session;
       url=url.replace("{APPID}",weChatConfig.getAppid());
       url=url.replace("{SECRET}",weChatConfig.getSecret());
       url=url.replace("{CODE}",weChatLoginDTO.getCode());

        String str = restTemplate.getForObject(url, String.class);
        OpenIdBO bo = JSON.parseObject(str, OpenIdBO.class);
        log.info("{}",bo);

        LambdaQueryWrapper<Member> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Member::getOpenId,bo.getOpenid());
        Member member = this.getOne(wrapper);

        //登录即注册
        if(member==null){
            //预防空指针
            member=new Member();
            member.setOpenId(bo.getOpenid());
            this.save(member);
        }

        //签发token

        return "token";
    }

    @Override
    public void sendCode(String phone) {
        Random random=new Random();
        String code=random.nextInt(8888)+1000+"";

        //todo 缓存验证码
        redisTemplate.opsForValue().set("code",code,60, TimeUnit.SECONDS);
        //获取redis中的验证码
        String s = redisTemplate.opsForValue().get("code");
        log.info("redis中获取的验证码是：{}",s);

        log.info("验证码是：{}",code);
    }
}
