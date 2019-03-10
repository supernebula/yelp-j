package com.yelp.dao.mapper.custom;

import com.yelp.entity.Permission;
import com.yelp.entity.Role;

import java.util.List;

public interface CustomRoleMapper {

    List<Role> getRolesByAdmin(String adminId);

    List<Role> getRolesByAdminUsername(String username);

    int changeAvailable(String roleId, int available);

}
