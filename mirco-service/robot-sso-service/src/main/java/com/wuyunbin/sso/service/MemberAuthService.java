package com.wuyunbin.sso.service;

import com.wuyunbin.sso.dto.LoginDTO;

public interface MemberAuthService {
    void sendCode(String phone);

    String login(LoginDTO loginDTO);
}
