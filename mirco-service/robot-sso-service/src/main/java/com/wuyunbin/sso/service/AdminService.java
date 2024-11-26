package com.wuyunbin.sso.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyunbin.sso.dto.AdminLoginDTO;
import com.wuyunbin.sso.entity.Admin;
import com.wuyunbin.sso.vo.AdminLoginVO;


/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author wuyunbin
 * @since 2024-10-27
 */
public interface AdminService extends IService<Admin> {

    AdminLoginVO login(AdminLoginDTO adminLoginDTO);
}
