package com.yelp.web.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * https://blog.csdn.net/huang906391/article/details/78376766/
 */
@Configuration
public class WebDefaultSecurityConfig extends WebMvcConfigurerAdapter {

    public final static String SESSION_KEY="username";

    @Bean
    public DefaultSecurityInterceptor getSecurityInterceptor(){
        return new DefaultSecurityInterceptor();
    }

    public void  addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/logout**");

        addInterceptor.addPathPatterns("/**");
    }

    /**
     * 自定义 Spring MVC中的拦截器/过滤器HandlerInterceptorAdapter
     */
    private class DefaultSecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();

//            判断是否已有该用户登录的session
            if(session.getAttribute(SESSION_KEY) != null){
                return true;
            }

//            跳转到登录页
            String url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }
}
