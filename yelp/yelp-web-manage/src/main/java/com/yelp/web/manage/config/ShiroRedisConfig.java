package com.yelp.web.manage.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.yelp.cache.redis.RedisCacheManager;
import com.yelp.security.dao.RedisSessionDAO;
import com.yelp.web.manage.config.shiro.CustomRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author April.Chen
 */
//@Slf4j
@Configuration
public class ShiroRedisConfig {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisSessionDAO sessionDAO;

//    @Bean
////    public UserRealm getUserRealm() {
////        return new UserRealm();
////    }


    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(1800);
        sessionManager.setCacheManager(redisCacheManager());
        return sessionManager;
    }


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        log.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);
        System.out.println("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //要求登录时的链接(可根据项目的URL进行替换),非必须的属性,默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //用户访问未对其授权的资源时,所显示的连接
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        /*定义shiro过滤器,例如实现自定义的FormAuthenticationFilter，需要继承FormAuthenticationFilter **本例中暂不自定义实现，在下一节实现验证码的例子中体现 */

        /*定义shiro过滤链 Map结构 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的 * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->

        filterChainDefinitionMap.put("/login", "anon");//anon 可以理解为不拦截
        filterChainDefinitionMap.put("/loginVerify", "anon");//anon 可以理解为不拦截
        filterChainDefinitionMap.put("/bower_components/**", "anon");
        filterChainDefinitionMap.put("/dist/**", "anon");
        filterChainDefinitionMap.put("/image/*", "anon");
        filterChainDefinitionMap.put("/kendoui/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/site/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");


//        filterChainDefinitionMap.put("/pages/**", "anon");
//        filterChainDefinitionMap.put("/api/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        //设置realm.
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

//    @Bean
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(securityManager());
//        return new AuthorizationAttributeSourceAdvisor();
//    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        //hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码

        return hashedCredentialsMatcher;
    }


//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
//        Map<String, String> filterChainDefinitionMap = new HashMap<>();
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        filterChainDefinitionMap.put("/sa/**", "authc");
//        filterChainDefinitionMap.put("/**", "anon");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
}
