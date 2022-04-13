package cn.com.springday3jdbc.component;

import cn.com.springday3jdbc.dao.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/4/6 11:42 下午
 * @description
 */
@Component
public class MappedFactoryBean {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

//    @Bean
    public MapperFactoryBean<UserDao> initMyBatisUserDao(){
        MapperFactoryBean<UserDao> bean = new MapperFactoryBean<>();
        bean.setSqlSessionFactory(sqlSessionFactory);
        bean.setMapperInterface(UserDao.class);
        return bean;
    }
}
