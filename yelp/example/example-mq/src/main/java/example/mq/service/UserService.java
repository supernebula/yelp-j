package example.mq.service;

import example.mq.domain.model.UserBean;


public  interface UserService {

    /**
     * 注册用户
     * @param user
     */
    void register(UserBean user);
}
