## 1、SpringBoot（约定优于配置）

在EJB（Enterprise Java Beans ）的生态下 开源出了SpringBoot框架

SpringBoot采用了”**约定优先配置**“的理念

怎么理解这个约定优先配置

SpringBoot依赖两个核心理念，一个是控制反转（Ioc），一个是面向且卖弄变成（Aop）

## 2、Ioc容器的理解

标准：首先Ioc容器都是实现了BeanFactiory这个接口的

Ioc容器有很多，ClassPathXmlApplicationContext,AnnotationConfigApplicationContext都是不同的ioc容器

BeanFactiory 中有几个重要的接口

```apl
getBean()、 # 方法名就给看出他是从ioc容器中获取bean对象的   可根据名称，类型来获取
getBeanProvider()、  # 获取Bean的创建者 
containsBean()、			# 是否包含Bean
isSingleton()、			# Bean是否为单例的   （单例工厂）
isPrototype()、			# Bean是否为原形的 		(原形模式） 克隆
isTypeMatch()、			# Bean是否与类型匹配
getType()、					# 获取Bean的类型
getAliases()、				# 获取Bean的别名

```

### 将Bean放入Ioc容器中

Bean在Ioc容器中默认都是以单例存在的也就是getBean他获取到同一类型的Bean都是同一个对象

```java
# 我们通过@Bean往Ioc容器中注册了一个Bean对象
@Configuration
public class AppConfig {

    @Bean(name = "user")
    public Users initUsers(){
        Users users = new Users();
        users.setId(1L);
        users.setUsername("cyx");
        users.setNote("note_1");
        return users;
    }
}


public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringDay1Application.class);
        Users bean = applicationContext.getBean(Users.class);
        logger.info(String.valueOf(bean.getId()));
    }

23:36:54.931 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
```

```java
# 也可以通过用@Component等注解来像ioc容器中注册Bean
@Component
public class Users {
    @Value("1")
    private Long id;
    @Value("cyx")
    private String username;
    @Value("node_1")
    private  String note;
}
```



@Component标明哪个类被扫描进入ioc容器。而 @ComponentScan则是标明采用何种策略去扫描装配Bean

```
@ComponentScan  默认扫描当前包和子包下所有的Bean或者可以指定扫描某个包下，
```

注意@SpringBootApplication这个注解其实本身就有@ComponentScan注解，也就是说他一开始就说去扫描同包与子包的Bean容器，当扫描到带有

```
@ComponentScan(value = {"com.ymf.springday1"},excludeFilters = {@ComponentScan.Filter(classes = Service.class)} )
```

理想状态有@Service注解的对象不会进入到Ioc容器中，其实不然，由于每个@ComponentScan都有自己的扫描目标，而@SpringBootApplication已经把对应的Service扫描进Ioc容器中了。

解决方案：在带有@SpringBootApplication注解的主配置类上加上这个配置即可

```java
@SpringBootApplication
@ComponentScan(value = {"com.ymf.springday1"},excludeFilters = {@ComponentScan.Filter(classes = Service.class)} )
public class SpringDay1Application extends SpringBootServletInitializer 
```

### 依赖注入

```
@Autowired
默认必须要找到一个Bean否则就会掏出异常
但是可以修改@Autowired的属性required为false
@Primary
当容器中同一个类型的Bean有多个时可以在想要优先注入的类中标注@Primary则SpringBoot扫描时则会优先扫描该Bean
@Qualifier
```

#### 资源定位的生命周期

```
1、资源定位
Spring通过我们的配置去扫描指定的路径下其中带有@Component的类。这个过程叫做资源定位
2、Bean定义
一旦找到资源那么Bean的信息会被保存到BeanDefinition的实例中去。（此时Bean并未被实例化），仅仅只是有个定义。
3、发布Bean的定义
然后就会把Bean的定义发布到Ioc容器中。此时Ioc容器中也只有Bean的定义。
4、实例化
5、依赖注入
```

在默认情况下，Spring会继续去完成Bean的实例化和依赖注入，这样Ioc容器中就可以得到一个依赖注入完成的Bean。但是有些时候我们还想希望是取出Bean时才对其进行初始化。

这是Spring在ComponentScan中提供了一个这样的配置 lazyInit（懒加载）默认为false，也就是默认不进行延迟加载。标注后Bean在被获取到时才会被初始化



我们要知道 IOC容器的标准为实现BeanFactory接口 ，但在生命周期中需要接口实现了ApplicationContext才会去执行对应ApplicationContextAware中的setApplicationContext接口

#### Bean的生命周期

```
1、首先Spring会去扫描@CompoentScan中指定路径下所有带@Compoent的类
2、找到@Compoent注解的类后，Bean的信息会被保存到BeanDefinition的实例中去（此时Bean并未被实例化），仅仅只是有个定义
3、然后Spring会把BeanDefinition中的信息发布到IOC容器中
4、实例化
	4.1、首页会执行BeanNameAware 接口中的setBeanName方法
	4.2、其次会执行BeanFactoryAware接口中的setBeanFactory方法
	4.3、然后会跟判断IOC容器是否实现ApplicationContext 如果实现了那么会执行ApplicationContextAware接口中的setApplicationContext 否则会跳过
	4.4、会执行BeanPostProcessor中实现的postProcessBeforeInitializtion(针对所有的Bean)
	4.5、执行带有@PostConstruct的自定义初始化方法
	4.6、执行InitializingBean中afterPropertiesSet(属性设置后)
	4.7、执行BeanPostProcessor中实现的postProcessAfterInitializtion(针对所有的Bean)
5、执行@PreDistroy指定的销毁方法
6、执行DisposableBean接口中的destroy方法
```

### 使用属性文件

配置文件

```properties
server.port=8090
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

database.url=jdbc:mysql://localhost:3306/test
database.username=root
database.password=70wzdxhn
database.driver=com.mysql.cj.jdbc.Driver


```



#### @Value

使用@Value 注入配置文件中的值

```java
@Component
public class DataSourceProperties {


    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;


    private String password;
    private String driver;

    public void setUrl(String url) {
        System.out.println(url);
        this.url = url;
    }

    public void setUsername(String username) {
        System.out.println(username);
        this.username = username;
    }

    @Value("${database.password}")
    public void setPassword(String password) {
        System.out.println(password);
        this.password = password;
    }
    @Value("${database.driver}")
    public void setDriver(String driver) {
        System.out.println(driver);
        this.driver = driver;
    }
```



#### @ConfigurationProperties

可以将大大减少对应的配置可以将对应的数据读入到pojo对象中

```java
package com.ymf.springday1.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuanmengfan
 * @date 2022/1/18 10:15 下午
 * @description
 */
@Component
@ConfigurationProperties("database")
public class DataSourceProperties {


//    @Value("${database.url}")
    private String url;

//    @Value("${database.username}")
    private String username;


    private String password;
    private String driver;

    public void setUrl(String url) {
        System.out.println(url);
        this.url = url;
    }

    public void setUsername(String username) {
        System.out.println(username);
        this.username = username;
    }

//    @Value("${database.password}")
    public void setPassword(String password) {
        System.out.println(password);
        this.password = password;
    }
//    @Value("${database.driver}")
    public void setDriver(String driver) {
        System.out.println(driver);
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}

```



#### @PropertiesSource

指定加载对应的配置文件，像db可以使用单独的配置文件配置值

属性ignoreResourceNotFound默认未false未找到配置文件的话会报错，，如果设置为true时则会忽略找到的配置

```java
@PropertySource(value = {"classpath:application.properties"})
```

### 条件装配

有些时候我们希望如果没有满足对应的条件时，有些Bean不需要导入到IOC容器中

#### @Conditional

```
属性：value   可指定多个Condition的类，满足其中条件时则会把bean加入到ioc容器中
```

### Bean的作用域

| 作用域类型    | 使用范围       | 作用域描述                                       |
| ------------- | -------------- | ------------------------------------------------ |
| singleton     | 所有Spring应用 | 默认值，Ioc容器中只存在单例                      |
| prototype     | 所有Spring应用 | 每当从Ioc容器中取出一个Bean，则创建一个新的 Bean |
| session       | Spring Web应用 | HTTP会话                                         |
| application   | Spring Web应用 | Web工程生命周期                                  |
| request       | Spring Web应用 | Web工程单次请求（request）                       |
| globalSession | Spring Web应用 |                                                  |

#### singleton作用域

下用ioc容器中拿到的Bean是一个

```java
 ScopeBean scopeBean1 = applicationContext.getBean(ScopeBean.class);
 ScopeBean scopeBean2 = applicationContext.getBean(ScopeBean.class);
 System.out.println("scopeBean1 == scopeBean1 " + (scopeBean1 == scopeBean2));

>scopeBean1 == scopeBean1 true
```



#### prototype作用域

下用ioc容器中都是重新创建的新的

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScopeBean {
}
ScopeBean scopeBean1 = applicationContext.getBean(ScopeBean.class);
 ScopeBean scopeBean2 = applicationContext.getBean(ScopeBean.class);
 System.out.println("scopeBean1 == scopeBean1 " + (scopeBean1 == scopeBean2));

>scopeBean1 == scopeBean1 false
```



#### request作用域

在单次请求中拿到的bean是同一个，两个不同的请求获取到的Bean是不同的

### @Profile

在运行对应的环境的时候才会将对应的bean加载的ioc容器中，否则不会

```java
@Bean
    @Profile(value = "test")
    @Conditional(DataSourceConditional.class)
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/test");
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("user", "root");
        properties.setProperty("password", "70wzdxhn");
        BasicDataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
```

### 引入XML配置Bean

**SpringBoot也兼容使用xml让ioc容器中注入bean**

#### @ImportResource

可**以使用ImportResource将SpringXml配置文件中配置的Bean放入到Ioc容器中**

```java
@ImportResource(locations = {"classpath:spring-other.xml"})
```

### EL表达式

使用#{}  可以开启EL表达式

```java
package com.ymf.springday1.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author yuanmengfan
 * @date 2022/1/18 11:54 下午
 * @description
 */
@Component
public class ElModel {

		// 可以使用类中的方法来拿到返回值注入到属性中 System是lang包中的所以不用写全类名
    @Value("#{T(System).currentTimeMillis()}")
    private Long initTime;
		
  	// 此外就要写全类名了
    @Value("#{T(java.util.UUID).randomUUID().toString()}")
    private String uuid;

  	// 注入字符串
    @Value("#{'EL 表达式'}")
    private String bds;
		
  	// 注入科学计算法
    @Value("#{9.3E3}")
    private double d;
  
		// 注入浮点型
    @Value("#{3.14}")
    private float pi;
  
		// 其他Bean对象中的属性
    @Value("#{dataSourceProperties.url}")
    private String url;
  
		// 比较运算 ？代表  dataSourceProperties.url不为空才去执行toUpperCase
    @Value("#{dataSourceProperties.url?.toUpperCase()}")
    private String upperCase;

		// 数学运算
    @Value("#{1+2}")
    private String fun;
		
  	// 方法比较
    @Value("#{dataSourceProperties.url?.contains('JDBC')}")
    private boolean contains;

  
  	// eq   字符串运算
    @Value("#{dataSourceProperties.url eq 'JDBC'}")
    private boolean eq;

  
  	// 字符串拼接
    @Value("#{dataSourceProperties.url+'1234567' }")
    private String appendStr;

  
  	// 三元运算
    @Value("#{dataSourceProperties.url eq 'JDBC' ? '123' : '1235'}")
    private String  aaa;

    @Override
    public String toString() {
        return "ElModel{" +
                "initTime=" + initTime +
                ", uuid='" + uuid + '\'' +
                ", bds='" + bds + '\'' +
                ", d=" + d +
                ", pi=" + pi +
                ", url='" + url + '\'' +
                ", upperCase='" + upperCase + '\'' +
                ", fun='" + fun + '\'' +
                ", contains=" + contains +
                ", eq=" + eq +
                ", appendStr='" + appendStr + '\'' +
                ", aaa='" + aaa + '\'' +
                '}';
    }
}

```

**总结：IOC容器是我们Bean的实例存放的地方，IOC帮我们去空值这边Bean的相互依赖，以及使用。**

## 3、AOP

### 约定编程

#### 1、@Aspect

声明一个切面

### 2、@Pointcut

声明一个切点

### 3、各通知的使用

1、@Before     

​		前置通知

2、@After

​		后置通知

3、@AfterReturning 

​		后置返回通知

4、@AfterThrowing

​		后置异常通知

5、@Around（不太好控制）

​		环绕通知		

​		该注解标志的方法如果其他没有回调proceed方法的话，不会触发原有的该出发的其他通知的功能

```Java
JoinPoint joinPoint
# 通知中都可使用该参数来获取target的参数等数据
```



### 4、增强接口

1、 @DeclareParents(value = "com.ymf.aop.service.impl.UserServiceImpl"
            ,defaultImpl = UserValidatorImpl.class)

为指定的类增加增强对应的接口操作

```Java
@Aspect
@Component
public class MyAspect {

    @DeclareParents(value = "com.ymf.aop.service.impl.UserServiceImpl"
            ,defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;
```

### 5、@Order

如果一个方法有多个切面时，则调用切面的顺序是混乱的，这时我们可以通过@Order注解来标注哪个切面首先执行，哪个切换后执行，也可以使用Ordered接口来实现该功能，前置通知都是从小到大运行的，而后者通知则是从大到小来执行的。



Aop总结：面向切面编程

场景：对数据库进行操作。

​			每次打开数据库连接时，我们都需要对对应的连接信息进初始化，每次关闭时都需要对该连接进行释放，每次执行sql发送错误时进行回滚，每次执行成功后对整个事务进行提交。

​			我们可以把公共部分进行提取，如初始化信息时，我们可以声明一个切面，并在执行sql前（切点）我们把对应的初始化信息完整。Spring使用JDK和CGLIB的动态代理来实现了aop这个功能，声明一个切面相当于把原有的对象通过接口的方法把当前对象代理成目标对象，从而使我们使用对应的方法实质也执行了每个切面的定义 的方法。JDK的动态代理是使用的接口来实现的，但cglib不是，不要被代理类去需要某一个接口，SpringBoot(2.6.3)默认好像使用的就是cglib的直接通过类来代理。

## 4、数据库操作

导入`spring-boot-starter-data-jpa`后 它就会默认为你配置数据源

### 1、JDBCTemplate

#### 配置文件

```YML
spring:
  datasource:
    username: root
    password: 70wzdxhn
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test
    hikari:
      maximum-pool-size: 50
      minimum-idle: 5
    type: com.zaxxer.hikari.HikariDataSource
```

#### 接口层

```java
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
```

#### 实现层

```java
package cn.com.springday3jdbc.service.impl;

import cn.com.springday3jdbc.pojo.SexEnum;
import cn.com.springday3jdbc.pojo.TUesr;
import cn.com.springday3jdbc.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author yuanmengfan
 * @date 2022/4/1 9:56 下午
 * @description
 */

@Service
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

  	// 获得对象的映射
    public RowMapper<TUesr> getUserMapping(){
        return (ResultSet rs, int rowNum) ->{
            TUesr tUesr = new TUesr();
            tUesr.setId(rs.getLong("id"));
            tUesr.setUserName(rs.getString("user_name"));
            tUesr.setSex(SexEnum.getEnumById(rs.getInt("sex")));
            tUesr.setNote(rs.getString("note"));
            return tUesr;
        };
    }


    @Override
    public TUesr getUser(Long id) {
        String sql = "select * from t_uesr where id = ?";
        TUesr tUesr = jdbcTemplate.queryForObject(sql, new Object[]{id}, getUserMapping());
        return tUesr;
    }

    @Override
    public List<TUesr> findUesrList(String name, String note) {
        String sql = "select * from t_uesr where user_name like concat('%',?,'%') and note like concat('%',?,'%') ";
        List<TUesr> query = jdbcTemplate.query(sql, getUserMapping(), name, note);
        return query;
    }

    @Override
    public int insertUser(TUesr tUesr) {
        String sql = "insert into  t_uesr (user_name, sex, note) values(?,?,?)";
        return jdbcTemplate.update(sql,tUesr.getUserName(),tUesr.getSex().getId(),tUesr.getNote());
    }

    @Override
    public int updateUser(TUesr tUesr) {
        String sql = "update t_uesr set user_name = ?,sex=?,note=? where id=?";
        return jdbcTemplate.update(sql,tUesr.getUserName(),tUesr.getSex().getId(),tUesr.getNote(),tUesr.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from  t_uesr where id=?";
        return jdbcTemplate.update(sql,id);
    }


}

```

### Spring的Jpd接口设计

![image-20220406231806686](/Users/yuanmengfan/Desktop/学习/笔记/i/image-20220406231806686.png)

其实我们是需要关注JpaRepository这个接口即可

Pojo

```Java
package cn.com.springday3jdbc.pojo;


import cn.com.springday3jdbc.component.convert.SexConverts;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "t_uesr")
public class User {

    private long id;
    private String userName;
    private SexEnum sex;
    private String note;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Convert(converter = SexConverts.class)
    @Column(name = "sex")
    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }


    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

```

Convert

```Java
package cn.com.springday3jdbc.component.convert;


import cn.com.springday3jdbc.pojo.SexEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converts;

/**
 * @author yuanmengfan
 * @date 2022/4/6 10:17 下午
 * @description
 */
public class SexConverts implements AttributeConverter<SexEnum,Integer> {


    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}

```



```Java
package cn.com.springday3jdbc.repository;

import cn.com.springday3jdbc.pojo.SexEnum;
import cn.com.springday3jdbc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanmengfan
 * @date 2022/4/6 10:23 下午
 * @description 继承了JpaRepository这个接口后，我们就可以直接使用对应的方法了，无需自己实现，也可以通过Jpa标准的命名方法来自定义查询方式
 */
@Repository
public interface JpaUserRepository extends JpaRepository<User,Integer> {

    List<User> getUsersByUserNameLikeAndNoteLike(String username, String note);

    List<User> findUsersBySexEquals(SexEnum sexEnum);

    User findUserByNoteEquals(String note);
}

```

### @EnableJpaRepositories 

注明`Spring`开启`Jpa`编程 Repository接口所在的包

### @EntityScan

```Java
@EntityScan(basePackages = "cn.com.springday3jdbc.pojo")
@EnableJpaRepositories(basePackages = "cn.com.springday3jdbc.repository")
```

POJO对象所在的包

### 整合MyBatis

#### #1、给SpringBoot中添加一下配置

```yml

mybatis:
  # MyBatis 映射文件配置
  mapper-locations: classpath:mapping/*Mapper.xml
  # MyBatis 扫描别名包，和注解@Alias联用
  type-aliases-package: cn.com.springday3jdbc.pojo1
  # MyBatis typeHandler扫描包 转换器
  type-handlers-package: cn.com.springday3jdbc.component.typeHandler
```

#2、声明POJO对象

使用`@Alias`标注别名 供MyBatis扫描

```java
package cn.com.springday3jdbc.pojo1;

import cn.com.springday3jdbc.pojo.SexEnum;
import org.apache.ibatis.type.Alias;

/**
 * @author yuanmengfan
 * @date 2022/4/6 11:24 下午
 * @description 
 */
@Alias("user_info")
public class UserInfo {
    private long id;
    private String userName;
    private SexEnum sex;
    private String note;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

```

#### #3、编写接口

```java
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
}
```

#### #4、编写MyBatis的xxxMapper.xml 配置文件

`namespace`:为对应的接口全类名

`select`:声明为一个查询方法

`id`:对应的方法名

`parameterType`:参数类型

`returnType`:返回值类型

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.com.springday3jdbc.dao.UserDao">
    <select id="getUserInfoById" parameterType="long" resultType="user_info">
        select * from t_uesr where id = #{id}
    </select>
</mapper>
```

#### #5、使用MapperFactoryBean来装配MyBatis接口

```Java
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

    @Bean
    public MapperFactoryBean<UserDao> initMyBatisUserDao(){
        MapperFactoryBean<UserDao> bean = new MapperFactoryBean<>();
        bean.setSqlSessionFactory(sqlSessionFactory);
        bean.setMapperInterface(UserDao.class);
        return bean;
    }
}

```

#### #6、测试

```java
		@Autowired
    UserDao userDao;

@GetMapping(path = "/mybatis_Quert_User_Id")
    @ResponseBody
    public Object mybatis_Quert_User_Id(HttpServletRequest request, @RequestParam Long id){
        return userDao.getUserInfoById(id);
    }
```

