package com.ymf.springday1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2021/12/26 6:45 下午
 * @description
 */
@Controller
public class TestController {


    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping(path = "/map")
    public Map<String,Object> map(){
        return jdbcTemplate.queryForList("select * from department").get(0);
    }
}
