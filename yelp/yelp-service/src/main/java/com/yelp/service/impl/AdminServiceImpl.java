package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.debug.MockAdmin;
import com.yelp.entity.Permission;
import com.yelp.entity.Role;
import com.yelp.searchParam.AdminSearchParam;
import com.yelp.dao.mapper.AdminMapper;
import com.yelp.entity.Admin;
import com.yelp.entity.AdminExample;
import com.yelp.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import evol.security.MD5Util;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

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


    public Admin getAdminByPwd(String id, String clearPassword){
        if(id == null)
            throw new NullPointerException("id");
        if(clearPassword == null)
            throw new NullPointerException("password");
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if(admin == null)
            return null;
        String digestPwd = MD5Util.MD5(clearPassword, admin.getSalt());
        if(!digestPwd.equals(admin.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return admin;
    }


    @Override
    public Admin login(String username, String clearPassword) {
        if(username == null)
            throw new NullPointerException("username");
        if(clearPassword == null)
            throw new NullPointerException("password");
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size() == 0)
            return null;
        Admin admin = list.get(0);
        String digestPwd = MD5Util.MD5(clearPassword, admin.getSalt());
        if(!digestPwd.equals(admin.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return admin;
    }

    public boolean updateLastloginIp(String id, String ip) {
        throw new RuntimeException("方法未实现");
    }

    @Override
    public boolean insert(Admin admin) {

        admin.setId(UUID.randomUUID().toString());
        admin.setSalt(UUID.randomUUID().toString());
        String digestPassword = MD5Util.MD5(admin.getPassword(), admin.getSalt());
        admin.setPassword(digestPassword);
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername());
        List<Admin> list = adminMapper.selectByExample(example);
        if(list.size() > 0)
            throw new RuntimeException("用户" + admin.getUsername() + "已存在，不能重复创建");
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

    /**
     * https://blog.csdn.net/u013435893/article/details/79596628
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = this.getAdminByUsername(username);
//        org.springframework.security.core.userdetails.User user
//                =  new org.springframework.security.core.userdetails.User(admin.getUsername()
//                ,"123456" //, admin.getPassword()
//                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

                org.springframework.security.core.userdetails.User user
                =  new org.springframework.security.core.userdetails.User(MockAdmin.USERNAME
                , MockAdmin.PASSWORD //, admin.getPassword()
                , AuthorityUtils.commaSeparatedStringToAuthorityList(MockAdmin.ROLE));

        return user;
    }

}
