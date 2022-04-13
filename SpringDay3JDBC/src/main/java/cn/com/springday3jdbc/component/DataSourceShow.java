package cn.com.springday3jdbc.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author yuanmengfan
 * @date 2022/2/28 11:14 下午
 * @description
 */
@Component
public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource bean = applicationContext.getBean(DataSource.class);
        System.out.println(bean.getClass().getName());
    }
}
