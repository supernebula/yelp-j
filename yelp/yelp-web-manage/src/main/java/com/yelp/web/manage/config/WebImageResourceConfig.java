package com.yelp.web.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebImageResourceConfig  extends WebMvcConfigurerAdapter {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
//        registry.addResourceHandler("/image/**").addResourceLocations("file:D://User/");
//        super.addResourceHandlers(registry);
        registry.addResourceHandler("/image/**").addResourceLocations("file:///Users/evol/datas/yelp/yelp_photos/photos/");
        super.addResourceHandlers(registry);
    }

}
