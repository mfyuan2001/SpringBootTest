package com.ymf.springday1.pojo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/1/12 11:37 下午
 * @description
 */
@Component
@Primary
public class Cat implements Animal{
    @Override
    public void use() {
        System.out.println("猫 【"+ Dog.class.getSimpleName()+"】");

    }
}
