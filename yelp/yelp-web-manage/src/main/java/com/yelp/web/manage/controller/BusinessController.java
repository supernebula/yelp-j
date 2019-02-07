package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("business")
public class BusinessController {

    @GetMapping({"index", "/", ""})
    public String index(Model model){
        return "business/index";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        return "business/detail";
    }
}
