package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"home", "/"})
public class HomeController {

    @GetMapping({"index", "/"})
    public String index(){
        return "home/index";
    }


    @RequestMapping({"testErr"})
    public String testErr(){

        throw new RuntimeException();
    }

    @RequestMapping("/login")
    public String login(){
        return "home/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        // 退出逻辑
        return "redirect:/login";
    }
}
