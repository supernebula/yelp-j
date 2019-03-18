package com.evol.sample.dao.impl;

import com.evol.sample.dao.UserRepository;
import com.evol.sample.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {


    public User GetUserById(String id) {
        User user = new User();
        user.setId("1");
        user.setUsername("zhangsan");
        user.setEmail("zhangsan@qq.com");
        return user;
    }
}
