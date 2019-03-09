package com.yelp.web.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @RequestMapping({"index/{roleid}", "/{roleid}"})
    public String index(Model model, @PathVariable(name = "roleid") String roleId){
        model.addAttribute("roleId", roleId);
        return "permission/index";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable String id){
        model.addAttribute("id", id);
        return "role/detail";
    }

    @RequestMapping("create/{roleid}")
    public String create(Model model, @PathVariable(name = "roleid") String roleId){
        return "permission/create";
    }

    @RequestMapping("edit/{id}")
    public String edit(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        return "role/edit";
    }
}

