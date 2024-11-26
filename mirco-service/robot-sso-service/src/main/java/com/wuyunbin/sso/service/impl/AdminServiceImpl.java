package com.wuyunbin.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wuyunbin.common.exceptions.BusinessException;
import com.wuyunbin.common.exceptions.enums.RespEnum;
import com.wuyunbin.sso.dto.AdminLoginDTO;
import com.wuyunbin.sso.entity.Admin;
import com.wuyunbin.sso.mapper.AdminMapper;
import com.wuyunbin.sso.service.AdminService;
import com.wuyunbin.sso.utils.JwtUtil;
import com.wuyunbin.sso.vo.AdminLoginVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {
        Admin admin = this.lambdaQuery().eq(Admin::getPhone, adminLoginDTO.getPhone()).one();
        if (admin == null) {
            throw new BusinessException(RespEnum.INVALID_ACCOUNT);
        }


        //签发token
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        String token = jwtUtil.createToken(map);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setToken(token);
        return adminLoginVO;
    }
}
