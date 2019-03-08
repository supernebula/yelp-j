package com.yelp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Permission;
import com.yelp.entity.Role;
import com.yelp.searchParam.RoleSearchParam;
import com.yelp.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    @Override
    public PageInfo<Role> Search(RoleSearchParam param) {
        return null;
    }

    @Override
    public List<Role> getRole(int offset, int rows) {
        return null;
    }

    @Override
    public Role getRole(String id) {
        return null;
    }

    @Override
    public List<Permission> getPermissions(String roleId) {
        return null;
    }
}
