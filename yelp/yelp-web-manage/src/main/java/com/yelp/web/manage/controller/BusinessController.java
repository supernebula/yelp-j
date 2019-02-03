package com.yelp.web.manage.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/business")
public class BusinessController {

    public String index(Model model){
        return "business/index";
    }

    public String detail(Model model){
        return "business/index";
    }

    public String create(Model model){
        return "business/index";
    }

    public String edit(Model model){
        return "business/index";
    }

}
