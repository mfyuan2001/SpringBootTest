package com.ymf.aop.controller;

import com.ymf.aop.model.User;
import com.ymf.aop.service.UserService;
import com.ymf.aop.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuanmengfan
 * @date 2022/2/27 3:51 下午
 * @description
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/printUser")
    @ResponseBody
    public User printUser(User user){
        UserValidator validator = (UserValidator) this.userService;
        if (validator.userValidate(user)) {
            this.userService.printUser(user);
        }
        return user;
    }
}
