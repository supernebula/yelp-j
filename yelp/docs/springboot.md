# springboot

## Spring Boot 表单验证

# [纯洁的微笑   Spring Boot 系列文章](http://www.ityouknow.com/spring-boot.html)

[Spring Boot 表单验证篇](https://www.bysocket.com/?p=1942)


校验框架 spring-boot-starter-validation
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <version>2.1.3.RELEASE</version>
</dependency>
```

## 对象转换java：dozer

Dozer 使用小结

https://www.cnblogs.com/jingmoxukong/p/5952447.html


## Spring Security

1. [Spring Boot 整合Spring Security](https://netfilx.github.io/spring-boot/11.springboot-security/springboot-security)

2. [SpringBoot + Spring Security 基本使用及个性化登录配置详解](https://www.jb51.net/article/140429.htm)

3. [Spring boot + Spring Security 实现用户登录管理](https://blog.csdn.net/wtopps/article/details/78297197)

4. [springboot系列：原始登录验证实现](https://blog.csdn.net/huang906391/article/details/78376766/)

## 静态文件服务器

[springboot 配置静态资源做简单的文件服务器](https://www.jianshu.com/p/4dd5be065f71)

## [spring boot 直接通过url访问获取内部或者外部静态资源图片](https://blog.csdn.net/ljj_9/article/details/79650008)

第一种方式，在代码上配置

```java
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    
        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
    
        registry.addResourceHandler("/image/**").addResourceLocations("file:D://User/");
        super.addResourceHandlers(registry);
     }
}

```

第二种方式，在配置文件中配置

```java
#资源映射路径为/image/**，你想在url访问的请求路径
spring.mvc.static-path-pattern=/image/**
#资源映射地址为file:D://User/，图片存放的真实路径

spring.resources.static-locations=file:D://User/ 
```



如下图，看浏览器的地址栏，框框标志第一部分是我的项目名，框框标志第二部分就是上面配置的映射路径（会映射到图片存放的真实路径），框框标志第三部分就是我的图片文件名，通过上面两种方式随便一种，就可以直接在浏览器通过url访问获取图片了。

![avatar](img/20180322101616947.png)


## ISSUE

[解决Springboot中给Interceptor配置的excludePathPatterns无效的问题](https://blog.csdn.net/turbo_zone/article/details/84454193)


# Integrating Apache Shiro 

[Integrating Apache Shiro into Spring-Boot Applications](http://shiro.apache.org/spring-boot.html)

[spring boot---------集成shiro(用户授权和权限控制)](https://www.vxzsk.com/769.html)

[Spring Boot 集成 Shiro权限框架](https://www.jianshu.com/p/63449d21b4b9)

[推荐！Spring Boot 集成-Shiro](https://segmentfault.com/a/1190000008847948)

[Shiro 加盐加密](https://www.jianshu.com/p/a052ac0e8102)


## shiro issue

[Apache Shiro IncorrectCredentialsException](https://stackoverflow.com/questions/35085418/apache-shiro-incorrectcredentialsexception)

hashService.generatePublicSalt设置为true，同时配置私有salt，这似乎是一个错误;

[关于spring整合shiro授权验证不通过的问题](https://www.oschina.net/question/589767_136768)

[org.apache.shiro.authc.IncorrectCredentialsException 异常处理](https://blog.csdn.net/qian19950120/article/details/78079762)

## 一、[springboot集成shiro时认证出现报错(Submitted credentials for token...)](https://blog.csdn.net/pan_fei/article/details/80693416)

springboot集成shiro时认证出现报错无非就是密码不匹配

可能发生的原因：

1. 前端传的密码是明文，而后台存储的是hash值，导致先后台不匹配报错

如果数据库储存的密码是加密的 那么要 从前端获取密码后，在Java里将其转换成hash值

2. 如果java已经将其加密，但仍然报错那就去ShiroConfig里面看凭证匹配器是不是set了hashIterations(2)

![shiro_code](img/shiro/2018061415335740.png)

 如果设置了2那么就是加密了两次 和你的密码肯定不匹配了，这个时候就把这行代码注释掉吧 因为默认就是1，散列一次

3. 可能是因为在UsernamePasswordToken内部将密码部分转为字符数组了，所以要这样取String password = new String((char[]) token.getCredentials()); ，此密码为明文，然后给密码加密String md5Pwd = new Md5Hash(password, username).toHex();，里面的username为salt,然后再将md5Pwd放到SimpleAuthenticationInfo中，下面贴一张图

![shiro_code2](img/shiro/20180614155023193.png)

这个异常对于对shiro还不太熟悉的我实在是难住了 看了一天的shiro才把问题解决 心塞塞.. 希望可以帮助亲们


## 缓存 Ehcache（进程内缓存框架）、 Redis