package com.wuyunbin.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyunbin.member.api.MemberInfoClient;
import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.sso.entity.Member;
import com.wuyunbin.sso.mapper.MemberMapper;
import com.wuyunbin.sso.service.MemberService;
import com.wuyunbin.sso.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MemberInfoClient memberInfoClient;

    @Override
    public MemberInfo findByToken() {
        String token = request.getHeader("Authorization");
        if (token.isBlank()) {
            throw new RuntimeException("token丢失");
        }
        token = token.replace("Bearer ", "");
        String valid = jwtUtil.validateToken(token);
        if (!"Valid".equals(valid)) {
            throw new RuntimeException("token异常");
        }
        Claims claims = jwtUtil.parseToken(token);
        String id = claims.get("id", String.class);
        MemberInfo memberInfo=memberInfoClient.getInfoById(id);
        return memberInfo;
    }

    @Override
    public String getMemberIdByToken(String token) {
        token = token.replace("Bearer ", "");
        String valid = jwtUtil.validateToken(token);
        if (!"Valid".equals(valid)) {
            throw new RuntimeException("token异常");
        }
        Claims claims = jwtUtil.parseToken(token);
        String id = claims.get("id", String.class);
        log.info("id:{}",id);
        return id;
    }
}
