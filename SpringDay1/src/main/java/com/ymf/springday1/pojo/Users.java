package com.ymf.springday1.pojo;

/**
 * @author yuanmengfan
 * @date 2022/1/11 11:30 下午
 * @description
 */
public class Users {
    private Long id;

    private String username;
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
