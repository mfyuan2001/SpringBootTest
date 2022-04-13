package com.ymf.springday1;

import com.ymf.springday1.pojo.BussinessPerson;
import com.ymf.springday1.pojo.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Properties;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(classes = {Service.class}) )
@PropertySource(value = {"classpath:db.properties"},ignoreResourceNotFound = true)
@ImportResource(locations = {"classpath:spring-other.xml"})
public class SpringDay1Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(properties);

        SpringApplication.run(SpringDay1Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringDay1Application.class);
    }
}
