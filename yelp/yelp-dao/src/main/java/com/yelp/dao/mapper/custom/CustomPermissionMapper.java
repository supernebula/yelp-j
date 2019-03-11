package com.yelp.dao.mapper.custom;

import com.yelp.entity.Permission;

import java.util.List;

public interface CustomPermissionMapper {

    List<Permission> getPermissionsByRole(String roleId);

    List<Permission> getPermissionsByAdmin(String adminId);

    List<Permission> getPermissionsByAdminName(String username);

    int changeAvailable(String permissionId, int available);
}
