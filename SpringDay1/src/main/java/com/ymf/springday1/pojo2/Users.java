package com.ymf.springday1.pojo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/1/11 11:30 下午
 * @description
 */
@Component
public class Users {
    @Value("1")
    private Long id;
    @Value("cyx")
    private String username;
    @Value("node_1")
    private  String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
