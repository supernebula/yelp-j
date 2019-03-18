package com.evol.sample.service.impl;

import com.evol.sample.dao.UserRepository;
import com.evol.sample.domain.model.User;
import com.evol.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User GetUserById(String id) {
        User user = userRepository.GetUserById(id);
        return user;
    }
}
