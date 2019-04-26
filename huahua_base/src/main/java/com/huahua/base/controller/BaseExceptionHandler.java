package com.huahua.base.controller;

import huahua.common.Result;
import huahua.common.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: admin
 * @Date: 2019/4/10 23:39
 * @Description:
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }

}
