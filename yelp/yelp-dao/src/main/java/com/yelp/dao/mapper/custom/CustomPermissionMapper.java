package com.yelp.dao.mapper.custom;

import com.yelp.entity.Permission;

import java.util.List;

public interface CustomPermissionMapper {

    List<Permission> getPermissionsByRole(String roleId);

    List<Permission> getPermissionsByAdmin(String adminId);

    int changeAvailable(String permissionId, int available);
}
