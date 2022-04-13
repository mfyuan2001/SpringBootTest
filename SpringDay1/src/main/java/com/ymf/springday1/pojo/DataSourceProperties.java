package com.ymf.springday1.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/1/18 10:15 下午
 * @description
 */
@Component
@ConfigurationProperties("database")
public class DataSourceProperties {


//    @Value("${database.url}")
    public  String url;

//    @Value("${database.username}")
    private String username;


    private String password;
    private String driver;

    public void setUrl(String url) {
        System.out.println(url);
        this.url = url;
    }

    public void setUsername(String username) {
        System.out.println(username);
        this.username = username;
    }

//    @Value("${database.password}")
    public void setPassword(String password) {
        System.out.println(password);
        this.password = password;
    }
//    @Value("${database.driver}")
    public void setDriver(String driver) {
        System.out.println(driver);
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
