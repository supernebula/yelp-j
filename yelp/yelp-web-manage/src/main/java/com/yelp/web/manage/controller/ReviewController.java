package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("review")
public class ReviewController {

    @GetMapping({"index", "/", ""})
    public String index(Model model){
        return "review/index";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "review/detail";
    }
}
