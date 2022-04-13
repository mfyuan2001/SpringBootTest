package cn.com.springday3jdbc.service.user.impl;

import cn.com.springday3jdbc.dao.UserDao;
import cn.com.springday3jdbc.pojo1.UserInfo;
import cn.com.springday3jdbc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanmengfan
 * @date 2022/4/12 11:55 下午
 * @description
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public UserInfo getUserInfoById(Long id) {
        return userDao.getUserInfoById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public int insertUser(UserInfo userInfo) {
        return userDao.insertUser(userInfo);
    }
}
