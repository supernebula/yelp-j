package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.component.AvailableStatus;
import com.yelp.dao.mapper.AdminRoleMapper;
import com.yelp.dao.mapper.RoleMapper;
import com.yelp.dao.mapper.custom.CustomRoleMapper;
import com.yelp.entity.*;
import com.yelp.searchParam.RoleSearchParam;
import com.yelp.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    private CustomRoleMapper customRoleMapper;

    private AdminRoleMapper adminRoleMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public RoleServiceImpl(RoleMapper roleMapper, CustomRoleMapper customRoleMapper, AdminRoleMapper adminRoleMapper){
        this.roleMapper = roleMapper;
        this.customRoleMapper = customRoleMapper;
        this.adminRoleMapper = adminRoleMapper;
    }

    @Override
    public PageInfo<Role> Search(RoleSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        RoleExample example = new RoleExample();

        RoleExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getName())) {criteria.andNameLike(param.getName());}
        if(param.getAvailable() != null) {criteria.andAvailableEqualTo(param.getAvailable().getCode());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Role> list = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Role> getRoles(int offset, int rows) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(offset, rows);
        List<Role> list = roleMapper.selectByExample(example);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public Role getRole(String id) {
        Role item = roleMapper.selectByPrimaryKey(id);
        return item;
    }

    @Override
    public List<Role> getRolesByAdmin(String adminId) {
        List<Role> list = customRoleMapper.getRolesByAdmin(adminId);
        return list;
    }

    @Override
    public List<Role> getRolesByAdminUsername(String username) {


        if(username == null)
            throw new NullPointerException("username");
        List<Role> roleList = customRoleMapper.getRolesByAdminUsername(username);
        return roleList;
    }

    @Override
    public boolean insert(Role role) {
        role.setId(UUID.randomUUID().toString());
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(role.getName());
        List<Role> list = roleMapper.selectByExample(example);
        if(list.size() > 0)
            throw new RuntimeException("角色" + role.getName() + "已存在，不能重复创建");
        int num = roleMapper.insert(role);
        return num > 0;
    }

    @Override
    public boolean udpate(Role role) {
        if(role == null)
            throw new NullPointerException("role");
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(role.getName());
        List<Role> list = roleMapper.selectByExample(example);
        if(list.size() > 0)
            throw new RuntimeException("角色" + role.getName() + "已存在，不能重复创建");
        int num = roleMapper.updateByExample(role, example);
        return num > 0;
    }

    @Override
    public boolean deleteById(String id) {
        int num = roleMapper.deleteByPrimaryKey(id);
        return num > 0;
    }

    @Override
    public boolean changeAvailable(String roleId, AvailableStatus available) {
        int num = customRoleMapper.changeAvailable(roleId, available.getCode());
        return num > 0;
    }

    @Override
    public boolean insertAdminRole(String adminId, String[] roleIds) {

        int num = 0;
        for (String roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setId(UUID.randomUUID().toString());
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            int num1 = adminRoleMapper.insert(adminRole);
            num += num1;
        }


        return  num > 0;
    }


}
