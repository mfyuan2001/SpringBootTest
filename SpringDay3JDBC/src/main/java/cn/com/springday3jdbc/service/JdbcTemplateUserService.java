package cn.com.springday3jdbc.service;

import cn.com.springday3jdbc.pojo.TUesr;

import java.util.List;

/**
 * @author yuanmengfan
 * @date 2022/4/1 9:51 下午
 * @description
 */
public interface JdbcTemplateUserService {

    TUesr getUser(Long id);

    List<TUesr> findUesrList(String name, String note);


    int insertUser(TUesr tUesr);

    int updateUser(TUesr tUesr);

    int deleteUser(Long id);

}
