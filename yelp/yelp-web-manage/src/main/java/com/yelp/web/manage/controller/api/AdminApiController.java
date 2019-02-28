package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.dao.mapper.AdminMapper;
import com.yelp.entity.Admin;
import com.yelp.service.AdminService;
import com.yelp.user.AdminSearchParam;
import com.yelp.web.manage.controller.param.admin.AdminChangePwdDto;
import com.yelp.web.manage.controller.param.admin.AdminCreateDto;
import com.yelp.web.manage.controller.param.admin.AdminUpdateDto;
import com.yelp.web.manage.controller.result.admin.AdminView;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import evol.security.MD5Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private AdminService adminService;

    @Autowired
    public AdminApiController(AdminService service){
        this.adminService = service;
    }

    private AdminView ConvertAdminToView(Admin admin){
        Mapper mapper = new DozerBeanMapper();
        AdminView destAdminView = mapper.map(admin, AdminView.class);
        return destAdminView;
    }

    private List<AdminView> ConvertAdminToView(List<Admin> admins){
        Mapper mapper = new DozerBeanMapper();
        ArrayList<AdminView> list = new ArrayList<>();
        for (Admin item : admins) {
            AdminView view = mapper.map(item, AdminView.class);
            list.add(view);
        }
        return list;
    }

    @GetMapping("search")
    public ApiResult<PageResult<AdminView>> search(AdminSearchParam param){
        PageInfo<Admin> pageInfo = adminService.Search(param);

        List<AdminView> adminViews = ConvertAdminToView(pageInfo.getList());
        PageResult<AdminView> result = new PageResult<>(adminViews, pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<AdminView> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Admin admin = adminService.getAdmin(id);
        AdminView adminView = ConvertAdminToView(admin);
        return ApiResult.success(adminView);

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
        admin.setMobile(dto.getMobile());
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
