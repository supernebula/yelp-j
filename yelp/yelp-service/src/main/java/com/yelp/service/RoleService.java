package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Permission;
import com.yelp.entity.Role;
import com.yelp.searchParam.PermissionSearchParam;
import com.yelp.searchParam.RoleSearchParam;

import javax.print.DocFlavor;
import java.util.List;

public interface RoleService {
    /**
     * 搜索角色
     * @param param 搜索条件
     * @return
     */
    PageInfo<Role> Search(RoleSearchParam param);


    /**
     * 从指定offset位置开始，获取指定数量的角色记录
     * @param offset
     * @param rows
     * @return
     */
    List<Role> getRole(int offset, int rows);

    /**
     * 根据id获取单条角色记录
     * @param id
     * @return
     */
    Role getRole(String id);

    /**
     * 获取指定角色的权限集合
     * @return
     */
    List<Permission> getPermissions(String roleId);
}
