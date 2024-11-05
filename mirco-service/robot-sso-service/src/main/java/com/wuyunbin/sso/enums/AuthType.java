package com.wuyunbin.sso.enums;

import lombok.Getter;

/**
 * @author wuyunbin
 */

@Getter
public enum AuthType {
    WECHAT("weChat", "weChatAuthService"),
    PASSWORD("password", "passwordAuthService"),
    VERIFICATION_CODE("verificationCode", "verificationCodeAuthService");
    private final String type;
    private final String className;

    AuthType(String type, String className) {
        this.type = type;
        this.className = className;
    }
}
