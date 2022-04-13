package com.ymf.springboot.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanmengfan
 * @date 2021/8/29 下午4:53
 * @description
 */
@Controller
@RequestMapping("/myWebjars")
public class MyDispatchServlet   {

    @Value("${domain}")
    private String domain;

    private static final Logger logger = LoggerFactory.getLogger(MyDispatchServlet.class);


    @RequestMapping(value = {"/**"})
    public String test(HttpServletRequest request) {
        //拿到请求的路径
        String requestURI = request.getRequestURI();
        //为了与webjars区分(避免重复的重定向) 判断请求路径是否为
        String webjars = requestURI.replace("/myWebjars", "/webjars");
        logger.info(webjars);
        //重定向到目标资源
        return "redirect:"+webjars;
    }
}
