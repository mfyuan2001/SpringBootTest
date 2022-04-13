package com.ymf.springday1;

import com.ymf.springday1.el.ElModel;
import com.ymf.springday1.pojo.Person;
import com.ymf.springday1.scope.ScopeBean;
import com.ymf.springday1.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

@SpringBootTest(classes = SpringDay1Application.class)
class SpringDay1ApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringDay1ApplicationTests.class);

    @Test
    void contextLoads() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDay1Application.class);
        UserService bean = applicationContext.getBean(UserService.class);
        logger.info(bean.toString());
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDay1Application.class);
        DataSource bean = applicationContext.getBean(DataSource.class);
        logger.info(String.valueOf(bean));
        Person person = applicationContext.getBean(Person.class);
        person.service();


        ScopeBean scopeBean1 = applicationContext.getBean(ScopeBean.class);
        ScopeBean scopeBean2 = applicationContext.getBean(ScopeBean.class);
        System.out.println("scopeBean1 == scopeBean1 " + (scopeBean1 == scopeBean2));


        ElModel elModel = applicationContext.getBean(ElModel.class);
        System.out.println(elModel);
    }

}
