package com.ymf.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yuanmengfan
 * @date 2021/11/4 11:23 下午
 * @description
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpSession session) {
        if (username != null && password != null && !"".equals(username) && "123456".equals(password)) {
            //登录成功，防止表单重复提交可以利用重定向到主页
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(1 * 10 * 60);
            return "redirect:/main.html";
        }
        request.setAttribute("msg", "账号或密码错误，请重新登录！");
        return "login";
    }
}
