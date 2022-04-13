package cn.com.springday3jdbc.web;

import cn.com.springday3jdbc.dao.UserDao;
import cn.com.springday3jdbc.pojo.SexEnum;
import cn.com.springday3jdbc.pojo.TUesr;
import cn.com.springday3jdbc.pojo1.UserInfo;
import cn.com.springday3jdbc.repository.JpaUserRepository;
import cn.com.springday3jdbc.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2022/4/1 10:14 下午
 * @description
 */
@Controller
public class TestController {


    @Autowired
    UserDao userDao;

    @Autowired
    JdbcTemplateUserService jdbcTemplateUserService;

    @Autowired
    JpaUserRepository jpaUserRepository;

    @PostMapping("/test")
    @ResponseBody
    public Object test(HttpServletRequest request){
        String user_name = request.getParameter("user_name");
        String sex = request.getParameter("sex");
        String note = request.getParameter("note");
        TUesr tUesr = new TUesr();
        tUesr.setUserName(user_name);
        tUesr.setSex(SexEnum.getEnumById(Integer.parseInt(sex)));
        tUesr.setNote(note);
        return jdbcTemplateUserService.insertUser(tUesr);
    }

    @GetMapping("/test_jpa_repository")
    @ResponseBody
    public Object test_jpa_repository(HttpServletRequest request){
        return jpaUserRepository.findAll();
    }


    @GetMapping("/test_jpa_custom_query_user")
    @ResponseBody
    public Object test_jpa_custom_query_user(HttpServletRequest request){
        return jpaUserRepository.getUsersByUserNameLikeAndNoteLike("%y%","%1%");
    }


    @GetMapping("/test_Jpa_Custom_Query_sexEquals_User")
    @ResponseBody
    public Object test_Jpa_Custom_Query_sexEquals_User(HttpServletRequest request){
        return jpaUserRepository.findUsersBySexEquals(SexEnum.MALE);
    }

    @GetMapping("/test_Jpa_Custom_Query_NoteLike_User")
    @ResponseBody
    public Object test_Jpa_Custom_Query_NoteLike_User(HttpServletRequest request){
        return jpaUserRepository.findUserByNoteEquals("小沙雕");

    }

    @GetMapping(path = "/mybatis_Quert_User_Id")
    @ResponseBody
    public Object mybatis_Quert_User_Id(HttpServletRequest request, @RequestParam Long id){
        return userDao.getUserInfoById(id);
    }

    @GetMapping(path = "/insert_UserInfo")
    @ResponseBody
    public Object insert_UserInfo(HttpServletRequest request, UserInfo userInfo){
        System.out.println(userInfo.getUserName()+"-"+userInfo.getNote());
        return userDao.insertUser(userInfo);
    }

}
