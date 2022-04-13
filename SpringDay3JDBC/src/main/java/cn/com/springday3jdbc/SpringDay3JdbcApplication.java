package cn.com.springday3jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = "cn.com.springday3jdbc.pojo")
@EnableJpaRepositories(basePackages = "cn.com.springday3jdbc.repository")
@MapperScan(
        // 指定扫描mapper接口的包路径
        basePackages = "cn.com.springday3jdbc.dao",
        // 指定sqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        // sqlSessionTemplateRef的优先级比sqlSessionFactoryRef高
        // 如果我们设置了sqlSessionTemplateRef 就会是sqlSessionFactoryRef 这个配置失效
        sqlSessionTemplateRef = "sqlSessionTemplate",
        // 指定限制的类型
        annotationClass = Repository.class

)
public class SpringDay3JdbcApplication {

    @Autowired
    PlatformTransactionManager transactionManager;

    public static void main(String[] args) {
        SpringApplication.run(SpringDay3JdbcApplication.class, args);
    }


    @PostConstruct
    public void showTransactionalManager(){
        System.out.println(transactionManager.getClass().getSimpleName());
    }

}
