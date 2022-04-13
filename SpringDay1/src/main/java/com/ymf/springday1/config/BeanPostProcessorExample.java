package com.ymf.springday1.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/1/17 11:26 下午
 * @description
 */
@Component
public class BeanPostProcessorExample implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+":"+bean);
        System.out.println("["+bean.getClass().getSimpleName()+"调用了前置处理方法BeanPostProcessor的postProcessBeforeInitialization接口]");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+":"+bean);
        System.out.println("["+bean.getClass().getSimpleName()+"调用了后置处理方法BeanPostProcessor的postProcessAfterInitialization接口 ]");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
