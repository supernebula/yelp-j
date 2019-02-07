package com.yelp.web.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class SystemController {

    @RequestMapping("error")
    public String error(){
        return "shared/error";
    }
}
