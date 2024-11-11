package com.wuyunbin.sso.handlers;

import com.wuyunbin.common.Result;
import com.wuyunbin.common.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wuyunbin
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public Result<Object> handleException(BusinessException e) {
        log.error("系统异常", e);
        return Result.error(e.getResp());
    }
}
