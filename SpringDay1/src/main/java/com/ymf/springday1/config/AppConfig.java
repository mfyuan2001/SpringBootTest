package com.ymf.springday1.config;

import com.ymf.springday1.conditional.DataSourceConditional;
import com.ymf.springday1.pojo.Users;
import com.ymf.springday1.service.UserService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yuanmengfan
 * @date 2022/1/11 11:31 下午
 * @description
 */
@Configuration
public class AppConfig {

//    @Bean(name = "user")
    public Users initUsers(){
        Users users = new Users();
        users.setId(1L);
        users.setUsername("cyx");
        users.setNote("note_1");
        return users;
    }

    @Bean
//    @Profile(value = "test")
    @Conditional(DataSourceConditional.class)
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("user", "root");
        properties.setProperty("password", "70wzdxhn");
        BasicDataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
