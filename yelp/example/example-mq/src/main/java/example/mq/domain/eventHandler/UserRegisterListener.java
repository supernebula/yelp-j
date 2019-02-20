package example.mq.domain.eventHandler;

import example.mq.domain.event.UserRegisterEvent;
import example.mq.domain.model.UserBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterListener {
    @EventListener
    public void registerHandle(UserRegisterEvent userRegisterEvent){
        UserBean user = userRegisterEvent.getUser();
        //业务逻辑
        //输出注册用户信息
        System.out.println(UserRegisterListener.class + ":@EventListener注册信息，用户名："+user.getUsername()+"，邮箱："+user.getEmail());
    }
}
