package example.mq.web.controller.api;

import example.mq.domain.model.UserBean;
import example.mq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @RequestMapping("api/user/register")
    public String register(UserBean user){

        //调用注册业务逻辑
        userService.register(user);
        return "注册成功.";
    }
}
