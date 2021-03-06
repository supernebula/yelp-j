//package com.yelp.web.manage.controller;
//
//import com.yelp.entity.Admin;
//import com.yelp.service.AdminService;
//import com.yelp.web.manage.controller.param.login.LoginDto;
//import evol.security.MD5Util;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.validation.Valid;
//
//@Controller
//public class ShiroLoginController {
//    private static final Logger logger = LoggerFactory.getLogger(ShiroLoginController.class);
//
//    private AdminService adminService;
//
//    public ShiroLoginController(AdminService adminService){
//        this.adminService = adminService;
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/loginVerify")
//    public String loginVerify(@Valid LoginDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            return "login";
//        }
//        String loginName = user.getUsername();
//        logger.info("准备登陆用户 => {}", loginName);
//
//        Admin admin = adminService.getAdminByUsername(loginName);
//
////        String saltPwd = MD5Util.MD5(user.getPassword(), admin.getSalt());
////        UsernamePasswordToken token = new UsernamePasswordToken(loginName, saltPwd);
//        UsernamePasswordToken token = new UsernamePasswordToken(loginName, user.getPassword());
//        //获取当前的Subject
//        Subject currentUser = SecurityUtils.getSubject();
//        try {
//            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
//            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
//            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
//            logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
//            currentUser.login(token);
//            logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
//        } catch (UnknownAccountException uae) {
//            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
//            redirectAttributes.addFlashAttribute("message", "未知账户");
//        } catch (IncorrectCredentialsException ice) {
//            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
//            redirectAttributes.addFlashAttribute("message", "密码不正确");
//        } catch (LockedAccountException lae) {
//            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
//            redirectAttributes.addFlashAttribute("message", "账户已锁定");
//        } catch (ExcessiveAttemptsException eae) {
//            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
//            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
//        } catch (AuthenticationException ae) {
//            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
//            logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
//            ae.printStackTrace();
//            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
//        }
//        //验证是否登录成功
//        if (currentUser.isAuthenticated()) {
//            logger.info("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//            return "redirect:/home/index";
//        } else {
//            token.clear();
//            return "redirect:/login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(RedirectAttributes redirectAttributes) {
//        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
//        SecurityUtils.getSubject().logout();
//        redirectAttributes.addFlashAttribute("message", "您已安全退出");
//        return "redirect:/login";
//    }
//
//
////    @GetMapping("/reg")
////    @ResponseBody
////    public Result<String> reg(@Valid User user, BindingResult bindingResult) {
////        if (bindingResult.hasErrors()) {
////            return Result.error("用户信息填写不完整");
////        }
//////        adminService.save(user);
//////        return Result.ok();
////    }
//}
