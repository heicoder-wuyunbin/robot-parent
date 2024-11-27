package com.wuyunbin.sso.service;

import com.wuyunbin.sso.dto.LoginDTO;
import com.wuyunbin.sso.vo.MemberLoginVO;

public interface MemberAuthService {
    void sendCode(String phone);

    MemberLoginVO login(LoginDTO loginDTO);
}
