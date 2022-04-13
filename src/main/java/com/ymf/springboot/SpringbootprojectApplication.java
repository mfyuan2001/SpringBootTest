package com.ymf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringbootprojectApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootprojectApplication.class, args);
    }
//    @Bean
//    public ViewResolver myViewResolvers(){
//        return new MyViewResolver();
//    }
//    private class  MyViewResolver implements  ViewResolver{
//
//        @Override
//        public View resolveViewName(String viewName, Locale locale) throws Exception {
//            return null;
//        }
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootprojectApplication.class);
    }
}
