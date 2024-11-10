package com.wuyunbin.sso.bo;

import lombok.Data;

@Data
public class OpenIdBO {
    private String session_key;
    private String openid;
}
