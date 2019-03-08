package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Permission;
import com.yelp.entity.Role;
import com.yelp.searchParam.UserSearchParam;
import com.yelp.entity.User;

import java.util.List;

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

    /**
     * 获取指定用户的角色集合
     * @return
     */
    List<Role> getRoleList(String userId);

    /**
     * 获取指定用户的权限集合
     * @return
     */
    List<Permission> getPermissionList(String userId);
}
