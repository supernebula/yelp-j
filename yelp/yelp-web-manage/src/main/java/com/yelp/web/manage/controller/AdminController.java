package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @RequestMapping({"index", "/"})
    public String index(Model model){
        return "admin/index";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/detail";
    }

    @RequestMapping("create")
    public String create(Model model){
        return "admin/create";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/edit";
    }

    @RequestMapping("changePwd/{id}")
    public String changePwd(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "admin/changePwd";
    }
}
