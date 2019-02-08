package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.business.UserSearchParam;
import com.yelp.entity.User;

public interface UserService {
    /**
     * 搜索用户
     * @param param 搜索条件
     * @return
     */
    PageInfo<User> Search(UserSearchParam param);

    /**
     * 根据id获取单个用户
     * @param id
     * @return
     */
    User getUser(String id);
}
