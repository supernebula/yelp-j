package com.yelp.web.manage.config;

import com.yelp.service.AdminService;
import com.yelp.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Spring boot + Spring Security 实现用户登录管理
 * https://blog.csdn.net/wtopps/article/details/78297197
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    AdminService adminService;

    @Autowired
    public WebSecurityConfig(AdminService adminService){
        this.adminService = adminService;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/somewhere/**");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                //定义当需要用户登录时候，转到的登录页面
                .loginPage("/login.html")
                .loginProcessingUrl("/api/admin/login")  //自定义的登录接口
                //登录成功后默认跳转到
                .defaultSuccessUrl("/home")
                .and()
                .authorizeRequests()    //定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html").permitAll() // 设置所有人都可以访问登录页面
                .anyRequest()           // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                //退出登录后的默认url是"/login"
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable(); // 关闭csrf防护

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserService()).passwordEncoder(passwordEncoder());
        //也可以将用户名密码写在内存，不推荐
        auth.inMemoryAuthentication().withUser("admin").password("111111").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *从数据库中读取用户信息
     */
    @Bean
    public UserDetailsService UserService() {
        return this.adminService;
    }

}
