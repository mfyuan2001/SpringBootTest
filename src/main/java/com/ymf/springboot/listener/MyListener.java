package com.ymf.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author yuanmengfan
 * @date 2021/11/16 11:04 下午
 * @description
 */
public class MyListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    //监听web应用创建
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("=== web容器开始启动 ===");
    }
    //监听web应用销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("=== web容器销毁中 ===");
    }
}
