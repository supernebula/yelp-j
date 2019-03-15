package com.yelp.web.manage.controller.api;

import com.yelp.entity.Admin;
import com.yelp.service.AdminService;
import com.yelp.web.manage.config.WebDefaultSecurityConfig;
import com.yelp.web.manage.controller.result.login.SessionUserInfoView;
import evol.common.api.ApiResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class LoginApiController {

    private AdminService adminService;

    @Autowired
    public LoginApiController(AdminService adminService){
        this.adminService = adminService;
    }

//    /**
//     * 基于Shiro的Session方式获取登录
//     * @param session
//     * @return
//     */
//    @GetMapping("currentUser")
//    public ApiResult<SessionUserInfoView> CurrentLoginUser(HttpSession session){
//
//        String username =  (String)SecurityUtils.getSubject().getPrincipal();
//
//        if(username == null)
//            return ApiResult.paramError("回话key为空");
//
//        Admin admin = adminService.getAdminByUsername(username);
//
//        if(admin == null)
//            return ApiResult.paramError("当前回话查不到用户记录");
//
//        SessionUserInfoView userInfo = new SessionUserInfoView();
//        userInfo.setUsername(admin.getUsername());
//        return ApiResult.success(userInfo);
//    }

    /**
     * 原始Session方式获取登录
     * @param session
     * @return
     */
    @GetMapping("currentUser")
    public ApiResult<SessionUserInfoView> CurrentLoginUser(HttpSession session){

        Object sessionKey = session.getAttribute(WebDefaultSecurityConfig.SESSION_KEY);
        if(sessionKey == null)
            return ApiResult.paramError("回话key为空");
        String username = (String)sessionKey;

        Admin admin = adminService.getAdminByUsername(username);

        if(admin == null)
            return ApiResult.paramError("当前回话查不到用户记录");

        SessionUserInfoView userInfo = new SessionUserInfoView();
        userInfo.setUsername(admin.getUsername());
        return ApiResult.success(userInfo);
    }
}
