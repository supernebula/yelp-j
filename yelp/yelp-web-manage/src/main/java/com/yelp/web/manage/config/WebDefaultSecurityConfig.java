package com.yelp.web.manage.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;

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

        addInterceptor.addPathPatterns("/**")
        .excludePathPatterns("/image/**")
        .excludePathPatterns("/swagger**")
        .excludePathPatterns("/swagger-ui.html")
        .excludePathPatterns("/error")
        .excludePathPatterns("/login**")
        .excludePathPatterns("/logout**");
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

        public void postHandle(
                HttpServletRequest request,
                HttpServletResponse response,
                Object handler,
                ModelAndView modelAndView) throws Exception {

        }

        public void afterCompletion(
                HttpServletRequest request,
                HttpServletResponse response,
                Object handler,
                Exception ex) throws Exception {

        }

    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
//        registry.addResourceHandler("/image/**").addResourceLocations("file:D://User/");
//        super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/**").addResourceLocations("file:///Users/evol/datas/yelp/yelp_photos/photos/");
        super.addResourceHandlers(registry);
    }



//    //获取配置文件中图片的路径
//    @Value("${local.fileserver.path}")
//    private String mImagesPath;
//    //访问图片方法
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if(mImagesPath.equals("") || mImagesPath.equals(mImagesPath)){
//            String imagesPath = WebDefaultSecurityConfig.class.getClassLoader().getResource("").getPath();
//            if(imagesPath.indexOf(".jar")>0){
//                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
//            }else if(imagesPath.indexOf("classes")>0){
//                imagesPath = "file:"+imagesPath.substring(0, imagesPath.indexOf("classes"));
//            }
//            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/"))+"/images/";
//            mImagesPath = imagesPath;
//        }
//        LoggerFactory.getLogger(WebDefaultSecurityConfig.class).info("imagesPath="+mImagesPath);
//        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
//        super.addResourceHandlers(registry);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /**
//         * @Description: 对文件的路径进行配置,创建一个虚拟路径/file/** ，即只要在<img src="/file/images/20180522/9aa64b2b-a558-421e-929c-537ff0aecdba.jpg" />便可以直接引用图片
//         *这是图片的物理路径 "file:/+本地图片的地址"
//         * @Date： Create in 14:08 2017/12/20
//         *
//         */
//        //读取配置文件中的上传路径
//
//        registry.addResourceHandler("/file/**").addResourceLocations("file:"+url);
//        super.addResourceHandlers(registry);
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String urlPrefixStr = "http://localhost:8080/pic/";
//        URL urlPrefix = null;
//        String urlPath = null;
//        String realPath = "C:/pic/yaoyunxiaoli/";
//        //获取图片URL前缀
//        try {
//            if (!picUploadProperties.getUrlPrefix().isEmpty()) {
//                urlPrefix = new URL(picUploadProperties.getUrlPrefix());
//            } else {
//                picUploadProperties.setUrlPrefix(urlPrefixStr);
//                urlPrefix = new URL(urlPrefixStr);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        //获取URL.path
//        if (urlPrefix != null) {
//            urlPath = urlPrefix.getPath();
//        }
//        //获取文件存储路径
//        if (!picUploadProperties.getRealpath().isEmpty()) {
//            realPath = picUploadProperties.getRealpath();
//        }else {
//            picUploadProperties.setRealpath(realPath);
//        }
//        //获取模块名，并且遍历添加URL与存储路径的映射
//        if (!picUploadProperties.getModule().isEmpty()){
//            for (String module: picUploadProperties.getModule().intern().split(",")
//            ) {
//                registry.addResourceHandler(urlPath+module+"/**").addResourceLocations("file:"+realPath+module+"/");
//            }
//        }
//        registry.addResourceHandler(urlPath+"/**").addResourceLocations("file:"+realPath);
//    }

}
