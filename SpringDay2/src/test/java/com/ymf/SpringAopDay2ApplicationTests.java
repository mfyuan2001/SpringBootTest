package com.ymf;

import com.ymf.aop.interceptor.MyInterceptor;
import com.ymf.aop.proxy.ProxyBean;
import com.ymf.aop.service.HelloService;
import com.ymf.aop.service.impl.HelloServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAopDay2ApplicationTests {

    @Test
    void contextLoads() {
        HelloService helloService = new HelloServiceImpl();
        HelloService target = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        target.sayHello("1111");

        System.out.println("##########");
        target.sayHello(null);
    }

}
