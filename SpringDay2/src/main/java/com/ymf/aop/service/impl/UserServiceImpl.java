package com.ymf.aop.service.impl;

import com.ymf.aop.model.User;
import com.ymf.aop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yuanmengfan
 * @date 2022/2/27 3:47 下午
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        System.out.println("======== Start =========");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getNode());
        System.out.println("======== End =========");
    }
}
