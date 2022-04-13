package com.ymf.springboot.exception;

/**
 * @author yuanmengfan
 * @date 2021/11/15 10:21 下午
 * @description
 */
public class UserNotExistException  extends RuntimeException{

    public UserNotExistException() {
        super("用户不存在");
    }
}
