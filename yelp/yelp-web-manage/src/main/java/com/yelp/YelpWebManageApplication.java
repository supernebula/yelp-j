package com.yelp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.yelp.dao.mapper"})
public class YelpWebManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(YelpWebManageApplication.class, args);
    }

}

