package com.wuyunbin.common.exceptions.enums;

import lombok.Getter;

/**
 * @author wuyunbin
 */

@Getter
public enum RespEnum {
    SUCCESS(20000, "success"),
    ERROR(-1, "失败"),
    TOKEN_ERROR(40001, "token错误"),
    TOKEN_EXPIRED(40002, "token过期"),
    TOKEN_SIGNATURE_INVALID(40003, "token签名无效"),
    TOKEN_MALFORMED(40004, "token格式错误"),
    TOKEN_MISSING(40005, "token丢失"),
    INVALID_ACCOUNT(40006, "非法的账号信息"),
    INVALID_PHONE(40007, "非法的手机号");


    private Integer code;
    private String message;

    private RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
