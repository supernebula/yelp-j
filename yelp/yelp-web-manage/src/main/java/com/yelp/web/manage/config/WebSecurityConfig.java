package com.yelp.web.manage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                //定义当需要用户登录时候，转到的登录页面
                .loginPage("/login")
                .loginProcessingUrl("/api/admin/login")  //自定义的登录接口
                .and()
                .authorizeRequests()    //定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll() // 设置所有人都可以访问登录页面
                .anyRequest()           // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable(); // 关闭csrf防护
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}
