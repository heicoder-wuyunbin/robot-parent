package com.wuyunbin.sso.dto;

import lombok.Data;

/**
 * @author wuyunbin
 */
@Data
public class SendSmsDTO {
    private String phone;
    private String type;
}
