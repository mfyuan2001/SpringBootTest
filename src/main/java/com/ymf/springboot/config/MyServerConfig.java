package com.ymf.springboot.config;

import com.ymf.springboot.filter.MyFilter;
import com.ymf.springboot.listener.MyListener;
import com.ymf.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

/**
 * @author yuanmengfan
 * @date 2021/11/16 10:47 下午
 * @description
 */
@Configuration
public class MyServerConfig {

    //配置嵌入式servlet容器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        //定制嵌入式的Servlet容器相关规则
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(9999);
            }
        };
    }

    //注册servlet
    @Bean
    public ServletRegistrationBean<MyServlet> servletServletRegistrationBean(){
        ServletRegistrationBean<MyServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(),"/helloworld");
        return servletServletRegistrationBean;
    }
    //注册filter
    @Bean
    public FilterRegistrationBean<MyFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        myFilterFilterRegistrationBean.setFilter(new MyFilter());
        myFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return myFilterFilterRegistrationBean;
    }

    //注册listener
    @Bean
    public ServletListenerRegistrationBean<MyListener> servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<MyListener> eventListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        eventListenerServletListenerRegistrationBean.setListener(new MyListener());
        return eventListenerServletListenerRegistrationBean;
    }

}
