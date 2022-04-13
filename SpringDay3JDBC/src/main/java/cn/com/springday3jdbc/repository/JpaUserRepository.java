package cn.com.springday3jdbc.repository;

import cn.com.springday3jdbc.pojo.SexEnum;
import cn.com.springday3jdbc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanmengfan
 * @date 2022/4/6 10:23 下午
 * @description
 */
@Repository
public interface JpaUserRepository extends JpaRepository<User,Integer> {

    List<User> getUsersByUserNameLikeAndNoteLike(String username, String note);

    List<User> findUsersBySexEquals(SexEnum sexEnum);

    User findUserByNoteEquals(String note);
}
