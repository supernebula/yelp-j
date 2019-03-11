//package com.yelp.web.manage.controller;
//
//import com.yelp.entity.Admin;
//import com.yelp.service.AdminService;
//import com.yelp.web.manage.config.WebDefaultSecurityConfig;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//
//@RequestMapping("/")
//@Controller
//public class DefaultLoginController {
//
//    private AdminService adminService;
//
//    public DefaultLoginController(AdminService adminService){
//        this.adminService = adminService;
//    }
//
//    @GetMapping("/")
//    public String index(@SessionAttribute(WebDefaultSecurityConfig.SESSION_KEY)String account, Model model){
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//    @PostMapping("/loginVerify")
//    public String loginVerify(String username, String password, HttpSession session){
//
//        Admin admin = adminService.login(username, password);
//        if (admin != null) {
//            session.setAttribute(WebDefaultSecurityConfig.SESSION_KEY, username);
//            return "redirect:/home";
//        } else {
//            return "redirect:/login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute(WebDefaultSecurityConfig.SESSION_KEY);
//        return "redirect:/login";
//    }
//}
