package com.ymf.aop.service.impl;

import com.ymf.aop.service.HelloService;

/**
 * @author yuanmengfan
 * @date 2022/1/20 11:42 下午
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if(name == null || "".equals(name)){
            throw new RuntimeException("name is null or blank");
        }
        System.out.println("hello " + name);
    }
}
