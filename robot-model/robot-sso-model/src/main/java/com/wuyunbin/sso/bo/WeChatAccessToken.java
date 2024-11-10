package com.wuyunbin.sso.bo;

import lombok.Data;

@Data
public class WeChatAccessToken {
    private String access_token;
    private Long expires_in;
}
