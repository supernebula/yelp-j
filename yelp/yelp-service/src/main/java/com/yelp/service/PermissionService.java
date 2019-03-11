package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.component.AvailableStatus;
import com.yelp.entity.Permission;
import com.yelp.searchParam.PermissionSearchParam;

import java.util.List;

public interface PermissionService {
    /**
     * 搜索权限
     * @param param 搜索条件
     * @return
     */
    PageInfo<Permission> Search(PermissionSearchParam param);


    /**
     * 从指定offset位置开始，获取指定数量的权限记录
     * @param offset
     * @param rows
     * @return
     */
    List<Permission> getPermissions(int offset, int rows);

    /**
     * 根据id获取单条权限记录
     * @param id
     * @return
     */
    Permission getPermission(String id);


    /**
     * 获取指定角色的权限集合
     * @return
     */
    List<Permission> getPermissionsByRole(String roleId);



    /**
     * 获取指定管理员的权限集合
     * @return
     */
    List<Permission> getPermissionsByAdmin(String adminId);

    public List<Permission> getPermissionsByAdminUsername(String username);

    public boolean insert(Permission admin, String roleId);

    public boolean udpate(Permission admin);

    public boolean deleteById(String id);

    public boolean changeAvailable(String permissionId, AvailableStatus available);
}
