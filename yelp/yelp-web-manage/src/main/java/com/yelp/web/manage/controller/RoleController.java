package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("role")
public class RoleController {
    @RequestMapping({"index", "/"})
    public String index(Model model){
        return "role/index";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "role/detail";
    }

    @RequestMapping("create")
    public String create(Model model){
        return "role/create";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "role/edit";
    }

    @RequestMapping("permission/{roleid}")
    public String permission(Model model, @PathVariable(name = "roleid") String roleId){
        model.addAttribute("roleId", roleId);
        return "role/permission";
    }
}
