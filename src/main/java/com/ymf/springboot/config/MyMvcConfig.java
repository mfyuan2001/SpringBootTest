package com.ymf.springboot.config;

import com.ymf.springboot.component.MyHandlerInterceptor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yuanmengfan
 * @date 2021/9/12 下午10:52
 * @description 使用WebMvcConfigurer可以扩展SpinrgMVC的功能
 * @EnableWebMvc 全面接管springmvc
 */

//@EnableWebMvc
@Configuration
class MyMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /ymf 请求来到success
        registry.addViewController("/ymf").setViewName("success");
    }

    /**
     * 增加隐射关系
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //SpringBoot会拦截静态资源 所以我们这里需要排除一下
                //登录拦截
                /*registry.addInterceptor(new MyHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/static/**")
                        .excludePathPatterns("/asserts/**")
                        .excludePathPatterns("/webjars/**")
                        .excludePathPatterns("/","/index","/index.html","/user/login");*/
            }
        };
        return  webMvcConfigurer;
    }



}

