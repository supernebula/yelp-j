package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.user.AdminSearchParam;
import com.yelp.dao.mapper.AdminMapper;
import com.yelp.entity.Admin;
import com.yelp.entity.AdminExample;
import com.yelp.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper mapper){
        this.adminMapper = mapper;
    }

    @Override
    public PageInfo<Admin> Search(AdminSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        AdminExample example = new AdminExample();

        AdminExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getUsername())) {criteria.andUsernameLike(param.getUsername());}
        if(!StringUtils.isEmpty(param.getEmail())) {criteria.andEmailLike(param.getEmail());}
        if(!StringUtils.isEmpty(param.getMobile())) {criteria.andMobileLike(param.getMobile());}


        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Admin> list = adminMapper.selectByExample(example);
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        if(username == null)
            throw new NullPointerException("username");
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Admin getAdmin(String id) {
        if(id == null)
            throw new NullPointerException("id");
        Admin item = adminMapper.selectByPrimaryKey(id);
        return item;
    }

    @Override
    public Admin getAdminByPwd(String username, String password) {
        if(username == null)
            throw new NullPointerException("username");
        if(password == null)
            throw new NullPointerException("password");
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Admin> list = adminMapper.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public boolean insert(Admin admin) {
        int num = adminMapper.insert(admin);
        return num > 0;
    }

    @Override
    public boolean udpate(Admin admin) {
        if(admin == null)
            throw new NullPointerException("admin");
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(admin.getId());
        int num = adminMapper.updateByExample(admin, example);
        return num > 0;
    }

    @Override
    public boolean deleteById(String id) {
        int num = adminMapper.deleteByPrimaryKey(id);
        return num > 0;
    }
}
