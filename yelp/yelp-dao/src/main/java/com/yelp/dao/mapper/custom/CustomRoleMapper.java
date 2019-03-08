package com.yelp.dao.mapper.custom;

import com.yelp.entity.Permission;

import java.util.List;

public interface CustomRoleMapper {

    List<Permission> getPermissionList(String userId);
}
