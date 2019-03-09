package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.component.AvailableStatus;
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
    List<Role> getRoles(int offset, int rows);

    /**
     * 根据id获取单条角色记录
     * @param id
     * @return
     */
    Role getRole(String id);

    /**
     * 获取指定（管理员）用户的角色集合
     * @return
     */
    List<Role> getRolesByAdmin(String adminId);


    public boolean insert(Role role);

    public boolean udpate(Role role);

    public boolean deleteById(String id);

    public boolean changeAvailable(String roleId, AvailableStatus available);

}
