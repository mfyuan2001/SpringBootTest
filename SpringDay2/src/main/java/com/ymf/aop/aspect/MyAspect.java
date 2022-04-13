package com.ymf.aop.aspect;

import com.ymf.aop.model.User;
import com.ymf.aop.validator.UserValidator;
import com.ymf.aop.validator.impl.UserValidatorImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author yuanmengfan
 * @date 2022/2/27 3:40 下午
 * @description
 */
@Aspect
@Component
public class MyAspect {


    @DeclareParents(value = "com.ymf.aop.service.impl.UserServiceImpl"
            ,defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut("execution(* com.ymf.aop.service.impl.UserServiceImpl.printUser(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before .......");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        proceedingJoinPoint.proceed();
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after .......");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("afterReturning .......");
    }


    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing .......");
    }
}
