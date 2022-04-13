package cn.com.springday3jdbc.dao;

import cn.com.springday3jdbc.pojo1.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author yuanmengfan
 * @date 2022/4/6 11:37 下午
 * @description
 */
@Repository
public interface UserDao {
    UserInfo getUserInfoById(Long id);

    int insertUser(UserInfo userInfo);
}
