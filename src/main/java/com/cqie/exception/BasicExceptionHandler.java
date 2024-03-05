package com.cqie.exception;

import com.cqie.utils.Result;
import com.cqie.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BasicExceptionHandler {


    //处理参数检验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result errorHandler(MethodArgumentNotValidException e) {
        String message = "";
        for (ObjectError s : e.getAllErrors()) {
            message += "[" + s.getDefaultMessage() + "]";
        }
        //获取所有校验异常信息进行拼接返回

        return Result.Failed(null, ResultCodeEnum.REQUEST_PARAM_ERROR.getCode(), message);
    }


    //处理全局异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result errorHandler(Exception e) {
        return Result.Failed(null, 500, e.getMessage());
    }
}