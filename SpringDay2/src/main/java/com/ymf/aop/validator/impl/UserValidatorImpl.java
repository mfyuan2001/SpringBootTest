package com.ymf.aop.validator.impl;

import com.ymf.aop.model.User;
import com.ymf.aop.validator.UserValidator;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/2/27 4:48 下午
 * @description
 */
@Component
public class UserValidatorImpl implements UserValidator {
    @Override
    public boolean userValidate(User user) {
        System.out.println("引入新的增强类"+UserValidator.class.getSimpleName());
        if(user !=null){
            return true;
        }
        return false;
    }
}
