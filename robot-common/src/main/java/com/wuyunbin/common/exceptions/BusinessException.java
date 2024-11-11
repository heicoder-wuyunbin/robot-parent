package com.wuyunbin.common.exceptions;


import com.wuyunbin.common.exceptions.enums.RespEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private RespEnum resp;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(RespEnum resp){
        super(resp.getMessage());
        this.resp=resp;
    }
}
