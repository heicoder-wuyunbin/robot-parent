package com.wuyunbin.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyunbin.sso.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}
