package com.ymf.aop.interceptor;


import java.lang.reflect.InvocationTargetException;

/**
 * @author yuanmengfan
 * @date 2022/1/20 11:45 下午
 * @description 拦截器
 */
public interface Interceptor {

    /**
     * 事前方法
     * @return
     */
    boolean before();

    /**
     * 事后方法
     * @return
     */
    void after();

    /**
     * 取代原有事件方法
     * @param invocation invocation --回调参数，可以通过他的proceed方法，回调原有方法
     * @return 原有事件返回对象
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;


    /**
     * 是否返回方法，事件没有发送异常后执行
     */
    void afterReturning();

    /**
     * 事后异常方法，当前事件发送异常后执行
     */
    void afterThrowing();

    /**
     * 是否使用around方法取代原有方法
     * @return
     */
    boolean useAround();
}
