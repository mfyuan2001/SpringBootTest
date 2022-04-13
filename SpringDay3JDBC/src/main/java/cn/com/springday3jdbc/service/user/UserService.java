package cn.com.springday3jdbc.service.user;

import cn.com.springday3jdbc.pojo1.UserInfo;

/**
 * @author yuanmengfan
 * @date 2022/4/12 11:54 下午
 * @description
 */
public interface UserService {
    UserInfo getUserInfoById(Long id);

    int insertUser(UserInfo userInfo);
}
