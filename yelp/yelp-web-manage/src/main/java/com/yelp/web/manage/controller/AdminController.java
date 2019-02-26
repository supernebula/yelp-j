package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    @GetMapping({"index", "/", ""})
    public String index(Model model){
        return "admin/index";
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/detail";
    }

    @GetMapping("create/{id}")
    public String create(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/create";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/edit";
    }

    @GetMapping("changePwd/{id}")
    public String changePwd(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/changePwd";
    }
}
