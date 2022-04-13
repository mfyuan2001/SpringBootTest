package com.ymf.springday1.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author yuanmengfan
 * @date 2022/1/18 11:54 下午
 * @description
 */
@Component
public class ElModel {

    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;

    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String uuid;

    @Value("#{'EL 表达式'}")
    private String bds;

    @Value("#{9.3E3}")
    private double d;

    @Value("#{3.14}")
    private float pi;

    @Value("#{dataSourceProperties.url}")
    private String url;

    @Value("#{dataSourceProperties.url?.toUpperCase()}")
    private String upperCase;


    @Value("#{1+2}")
    private String fun;

    @Value("#{dataSourceProperties.url?.contains('JDBC')}")
    private boolean contains;

    @Value("#{dataSourceProperties.url eq 'JDBC'}")
    private boolean eq;

    @Value("#{dataSourceProperties.url+'1234567' }")
    private String appendStr;

    @Value("#{dataSourceProperties.url eq 'JDBC' ? '123' : '1235'}")
    private String  aaa;

    @Override
    public String toString() {
        return "ElModel{" +
                "initTime=" + initTime +
                ", uuid='" + uuid + '\'' +
                ", bds='" + bds + '\'' +
                ", d=" + d +
                ", pi=" + pi +
                ", url='" + url + '\'' +
                ", upperCase='" + upperCase + '\'' +
                ", fun='" + fun + '\'' +
                ", contains=" + contains +
                ", eq=" + eq +
                ", appendStr='" + appendStr + '\'' +
                ", aaa='" + aaa + '\'' +
                '}';
    }
}
