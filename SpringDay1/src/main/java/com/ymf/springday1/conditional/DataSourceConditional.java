package com.ymf.springday1.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author yuanmengfan
 * @date 2022/1/18 11:06 下午
 * @description
 */
public class DataSourceConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.containsProperty("url")
                ||environment.containsProperty("username")
                ||environment.containsProperty("password")
                ||environment.containsProperty("driver");

    }
}
