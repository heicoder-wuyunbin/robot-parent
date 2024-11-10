package com.wuyunbin.sso.dto;

import lombok.Data;

/**
 * @author wuyunbin
 */
@Data
public class LoginDTO {
    private String phone;
    private String password;
    private String code;
    private String type;
}
