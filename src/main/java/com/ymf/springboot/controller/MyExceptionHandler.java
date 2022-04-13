package com.ymf.springboot.controller;

import com.ymf.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2021/11/15 10:28 下午
 * @description  异常处理器
 * @ControllerAdvice 系统抛出异常时会执行@ExceptionHandler注解标注的方法
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public String  handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //javax.servlet.error.status_code 设置该属性的值为4xx或5xx

        map.put("code", "user.not exist");
        map.put("messenger", e.getMessage());
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,400);
        request.setAttribute("myCode",map);
        return "forward:/error";
    }
}
