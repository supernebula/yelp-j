package com.basic;

import com.alibaba.fastjson.JSON;
import com.yelp.entity.Admin;
import com.yelp.web.manage.controller.result.admin.AdminView;
import evol.util.JSONUtil;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.json.JsonContent;

import java.util.Date;
import java.util.UUID;


public class DozerMapperTest {

    @Test
    public void Test1(){

        Admin admin = new Admin();
        admin.setId(UUID.randomUUID().toString());
        admin.setUsername("username1");
        admin.setPassword("password1");
        admin.setEmail("wewe@qq.com");
        admin.setMobile("12233332222");
        admin.setSalt("salt22");
        admin.setCreateTime(new Date());
        admin.setLastLoginTime(new Date());

        Mapper mapper = new DozerBeanMapper();
        AdminView destAdminView = mapper.map(admin, AdminView.class);

        System.out.println("admin:");
        String adminJson = JSONUtil.parse2Json(admin, Admin.class);
        System.out.println(adminJson);

        System.out.println("destAdminView:");
        String adminViewJson = JSONUtil.parse2Json(destAdminView, AdminView.class);
        System.out.println(adminViewJson);

        Assert.assertTrue(destAdminView.getId().equals(admin.getId())
        && destAdminView.getUsername().equals(admin.getUsername())
        && destAdminView.getEmail().equals(admin.getEmail())
        && destAdminView.getMobile().equals(admin.getMobile())
        && destAdminView.getCreateTime().equals(admin.getCreateTime())
        && destAdminView.getLastLoginTime().equals(admin.getLastLoginTime()));




    }
}
