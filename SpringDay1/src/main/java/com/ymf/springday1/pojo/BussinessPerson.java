package com.ymf.springday1.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yuanmengfan
 * @date 2022/1/12 11:32 下午
 * @description
 */
@Component
public class BussinessPerson  implements Person, BeanNameAware, BeanFactoryAware
        , ApplicationContextAware, InitializingBean, DisposableBean {


    Animal animal;

    @Override
    public void service() {
        animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BussinessPerson( @Autowired(required = false)
                            @Qualifier(value = "dog") Animal animal) {
        System.out.println("延迟依赖注入");
        this.animal = animal;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
        System.out.println("["+this.getClass().getSimpleName()+"调用了BeanNameAware的setBeanName接口]");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory);
        System.out.println("["+this.getClass().getSimpleName()+"调用了BeanFactoryAware的setBeanFactory接口]");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        System.out.println("["+this.getClass().getSimpleName()+"调用了ApplicationContextAware的setApplicationContext接口]");
    }


    @PostConstruct
    public void initPostConstruct(){
        System.out.println("["+this.getClass().getSimpleName()+"调用了 @PostConstruct 的 自定义初始化方法]");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("["+this.getClass().getSimpleName()+"调用了InitializingBean的afterPropertiesSet接口 ]");
    }



    @PreDestroy
    public void preDestroy(){
        System.out.println("["+this.getClass().getSimpleName()+"调用了@PreDestroy 的的 自定义销毁方法]");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("["+this.getClass().getSimpleName()+"调用了DisposableBean 的的 destroy接口]");
    }
}
