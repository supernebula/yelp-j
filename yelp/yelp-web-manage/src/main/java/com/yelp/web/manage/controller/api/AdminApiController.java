package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Admin;
import com.yelp.service.AdminService;
import com.yelp.user.AdminSearchParam;
import com.yelp.web.manage.controller.param.admin.AdminChangePwdDto;
import com.yelp.web.manage.controller.param.admin.AdminCreateDto;
import com.yelp.web.manage.controller.param.admin.AdminUpdateDto;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import evol.security.MD5Util;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private AdminService adminService;

    @Autowired
    public AdminApiController(AdminService service){
        this.adminService = service;
    }

    @GetMapping("search")
    public ApiResult<PageResult<Admin>> search(AdminSearchParam param){
        PageInfo<Admin> pageInfo = adminService.Search(param);
        PageResult<Admin> result = new PageResult<>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<Admin> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Admin item = adminService.getAdmin(id);
        return ApiResult.success(item);

    }

    @PostMapping("create")
    public ApiResult<Object> createPost(AdminCreateDto dto){
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID().toString());
        admin.setUsername(dto.getUsername());
        admin.setSalt(UUID.randomUUID().toString());
        String digestPassword = MD5Util.MD5(dto.getPassword(), admin.getSalt());
        admin.setPassword(digestPassword);
        admin.setEmail(dto.getEmail());
        admin.setCreateTime(new Date());
        boolean flag = adminService.insert(admin);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @PostMapping("edit")
    public ApiResult<Object> editPost(@PathVariable String id, AdminUpdateDto dto){
        if(!id.equals(dto.getId()))
            throw new RuntimeException("用户id参数错误");
        Admin admin = adminService.getAdmin(dto.getId());
        if(admin == null)
            throw new RuntimeException("用户不存在");
        admin.setEmail(dto.getEmail());
        admin.setMobile(dto.getMobile());
        boolean flag = adminService.udpate(admin);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @DeleteMapping("delete")
    public ApiResult<Object> delete(String id){
        boolean flag = adminService.deleteById(id);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @PutMapping("changePassword")
    public ApiResult<Object> changePassword(@PathVariable() String id, @RequestBody AdminChangePwdDto dto){
        Admin admin = adminService.getAdminByPwd(dto.getUsername(), dto.getPassword());
        if(admin == null)
            throw new RuntimeException("原始密码错误");
        if(!admin.getId().equals(id))
            throw new RuntimeException("用户id参数错误");
        if(!dto.getNewPassword().equals(dto.getConfirmPassword()))
            throw new RuntimeException("两次新密码输入不同");
        String digestPassword = MD5Util.MD5(dto.getNewPassword(), admin.getSalt());
        admin.setPassword(digestPassword);
        boolean flag = adminService.udpate(admin);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

}
