package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.component.AvailableStatus;
import com.yelp.dao.mapper.PermissionMapper;
import com.yelp.dao.mapper.custom.CustomPermissionMapper;
import com.yelp.entity.Permission;
import com.yelp.entity.PermissionExample;
import com.yelp.entity.Role;
import com.yelp.entity.RoleExample;
import com.yelp.searchParam.PermissionSearchParam;
import com.yelp.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PermissionServiceImpl implements PermissionService {



    private PermissionMapper permissionMapper;

    private CustomPermissionMapper customPermissionMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper
            , CustomPermissionMapper customPermissionMapper){
        this.permissionMapper = permissionMapper;
        this.customPermissionMapper = customPermissionMapper;
    }


    @Override
    public PageInfo<Permission> Search(PermissionSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        PermissionExample example = new PermissionExample();

        PermissionExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getName())) {criteria.andNameLike(param.getName());}
        if(!StringUtils.isEmpty(param.getResourceType())) {criteria.andResourceTypeEqualTo(param.getResourceType());}
        if(!StringUtils.isEmpty(param.getUrl())) {criteria.andUrlLike(param.getUrl());}
        if(!StringUtils.isEmpty(param.getPermission())) {criteria.andPermissionLike(param.getPermission());}
        if(param.getAvailable() != null) {criteria.andAvailableEqualTo(param.getAvailable().getCode());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Permission> list = permissionMapper.selectByExample(example);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Permission> getPermissions(int offset, int rows) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(offset, rows);
        List<Permission> list = permissionMapper.selectByExample(example);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public Permission getPermission(String id) {

        Permission item = permissionMapper.selectByPrimaryKey(id);
        return item;
    }

    @Override
    public List<Permission> getPermissionsByRole(String roleId) {
        List<Permission> list = customPermissionMapper.getPermissionsByRole(roleId);
        return list;
    }

    @Override
    public List<Permission> getPermissionsByAdmin(String adminId) {
        List<Permission> list = customPermissionMapper.getPermissionsByAdmin(adminId);
        return list;
    }

    @Override
    public boolean insert(Permission permission) {
        permission.setId(UUID.randomUUID().toString());
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(permission.getName());
        List<Permission> list = permissionMapper.selectByExample(example);
        if(list.size() > 0)
            throw new RuntimeException("权限" + permission.getName() + "已存在，不能重复创建");
        int num = permissionMapper.insert(permission);
        return num > 0;
    }

    @Override
    public boolean udpate(Permission permission) {
        if(permission == null)
            throw new NullPointerException("permission");
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(permission.getName());
        List<Permission> list = permissionMapper.selectByExample(example);
        if(list.size() > 0)
            throw new RuntimeException("权限" + permission.getName() + "已存在，不能重复创建");
        int num = permissionMapper.updateByExample(permission, example);
        return num > 0;
    }

    @Override
    public boolean deleteById(String id) {
        int num = permissionMapper.deleteByPrimaryKey(id);
        return num > 0;
    }

    @Override
    public boolean changeAvailable(String permissionId, AvailableStatus available) {
        int num = customPermissionMapper.changeAvailable(permissionId, available.getCode());
        return num > 0;
    }
}
