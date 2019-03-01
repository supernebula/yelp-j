package com.yelp.web.manage.handler;

import evol.common.api.ApiResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler(value = { RuntimeException.class })
    public ApiResult<Object> business(RuntimeException ex) {
        return ApiResult.paramError("业务异常:"+ex.getMessage());
    }

//    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
//    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        String field=ex.getBindingResult().getFieldError().getField().replace("parameter.","");
//        String message=ex.getBindingResult().getFieldError().getDefaultMessage();
//        return Result.fail("验证失败:字段["+field+"]"+message);
//    }
//
//    @ExceptionHandler(value = { Exception.class })
//    public Result exception(Exception ex) {
//        return Result.fail("系统异常:"+ex.getMessage());
//    }
}
