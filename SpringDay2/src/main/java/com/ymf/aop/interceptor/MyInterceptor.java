package com.ymf.aop.interceptor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yuanmengfan
 * @date 2022/1/21 12:06 上午
 * @description
 */
public class MyInterceptor implements  Interceptor{
    @Override
    public boolean before() {
        System.out.println("before .....");
        return true;
    }

    @Override
    public boolean useAround() {
        return true;
    }

    @Override
    public void after() {
        System.out.println("after .....");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("around before /////");
        Object proceed = invocation.proceed();
        System.out.println("around after /////");
        return proceed;
    }

    @Override
    public void afterReturning() {
        System.out.println("afterReturning....");
    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing....");
    }


}