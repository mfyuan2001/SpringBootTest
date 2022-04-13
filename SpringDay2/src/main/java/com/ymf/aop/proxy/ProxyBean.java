package com.ymf.aop.proxy;

import com.ymf.aop.interceptor.Interceptor;
import com.ymf.aop.interceptor.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author yuanmengfan
 * @date 2022/1/21 12:09 上午
 * @description
 */
public class ProxyBean implements InvocationHandler {
    /**
     * 保存原有对象
     */
    private Object target = null;

    /**
     * 保存原有拦截器
     */
    private Interceptor interceptor = null;


    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();

        proxyBean.target = target;
        proxyBean.interceptor = interceptor;

        /**
         * 代理方法
         * 1、被代理类的加载器
         * 2、被代理类实现的接口
         * 3、绑定代理对象逻辑的实现
         * 被代理的对应如果执行方法会执行 InvocationHandler 中的 invoke 方法 所以如果我们像对代理对象进行对应的操作时，我们在此接口中编写对应逻辑即可
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxyBean);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean exceptionFlag = false;

        // 此处的target 是原先被代理的对象中原有的对象
        Invocation invocation = new Invocation(args,method,target);

        Object retObj = null;
        this.interceptor.before();
        try {
            if(this.interceptor.useAround()){
                retObj = this.interceptor.around(invocation);
            }else {
                // 如果拦截器中的useAround 为false时会调用原始对象的invoke方法
                retObj = method.invoke(target,args);
            }
        }catch (Exception e){
            exceptionFlag = true;
        }

        this.interceptor.after();

        if (exceptionFlag) {
            this.interceptor.afterThrowing();
        }else{
            this.interceptor.afterReturning();
            return retObj;
        }
        return null;
    }
}
