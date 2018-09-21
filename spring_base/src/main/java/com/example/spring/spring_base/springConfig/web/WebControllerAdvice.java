package com.example.spring.spring_base.springConfig.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/1 15:07
 * @Version: 1.0
 * modified by:
 */
@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public Map handleException(Exception e){
        e.printStackTrace();
        Map map = new HashMap();
        map.put("name","白素贞");
        map.put("age",1000);
        return map;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        int errorCode = 700004 ;
    }

    @ExceptionHandler(BindException.class)
    public void handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        int errorCode = 700005 ;
    }
}
