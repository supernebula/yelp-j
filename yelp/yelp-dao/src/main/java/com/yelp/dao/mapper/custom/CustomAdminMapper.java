package com.yelp.dao.mapper.custom;

import com.yelp.entity.Permission;
import com.yelp.entity.Role;

import java.util.List;

public interface CustomAdminMapper {

    List<Role> getRoleList(String userId);

    List<Permission> getPermissionList(String userId);
}
