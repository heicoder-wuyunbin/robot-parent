package com.wuyunbin.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wuyunbin.sso.entity.Admin;
import com.wuyunbin.sso.mapper.AdminMapper;
import com.wuyunbin.sso.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
