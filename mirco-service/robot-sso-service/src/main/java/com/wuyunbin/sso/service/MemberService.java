package com.wuyunbin.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyunbin.member.entity.MemberInfo;
import com.wuyunbin.sso.dto.LoginDTO;

import com.wuyunbin.sso.entity.Member;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
public interface MemberService extends IService<Member> {


    MemberInfo findByToken();

    String getMemberIdByToken();
}
