package com.evol.sample.dao;

import com.evol.sample.domain.model.User;

public interface UserRepository {

    public User GetUserById(String id);
}
