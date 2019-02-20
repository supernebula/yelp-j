package example.mq.service.impl;

import example.mq.domain.event.UserRegisterEvent;
import example.mq.domain.model.UserBean;
import example.mq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ApplicationContext applicationContext;

    public void register(UserBean user){
        
        applicationContext.publishEvent(new UserRegisterEvent(this, user));
    }

}
