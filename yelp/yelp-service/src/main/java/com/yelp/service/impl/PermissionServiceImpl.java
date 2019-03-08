package com.yelp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Permission;
import com.yelp.searchParam.PermissionSearchParam;
import com.yelp.service.PermissionService;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public PageInfo<Permission> Search(PermissionSearchParam param) {
        return null;
    }

    @Override
    public List<Permission> getPermission(int offset, int rows) {
        return null;
    }

    @Override
    public Permission getPermission(String id) {
        return null;
    }
}
