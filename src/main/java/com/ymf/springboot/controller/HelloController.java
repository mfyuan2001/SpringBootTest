package com.ymf.springboot.controller;

import com.ymf.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2021/8/25 下午11:15
 * @description
 */
@Controller
public class HelloController {
    @RequestMapping("hello")
    @ResponseBody
    public String hello(@RequestParam  String userId){
        if(userId==null||"".equals(userId)){
            throw new UserNotExistException();
        }
        return "hello world";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello", "<h1>你好世界</h1>");
        map.put("users", Arrays.asList("zhang","li","wang"));
        return "success";
    }

    @RequestMapping("/abc")
    public String abc(Map<String,Object> map){
        return "succese";
    }


}
