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
    public void createPost(AdminCreateDto dto){

    }

    @PostMapping("edit")
    public void editPost(AdminUpdateDto dto){

    }

    @DeleteMapping("delete")
    public void delete(String id){
        adminService.deleteById(id);
    }

    @PutMapping("changePassword")
    public boolean changePassword(@PathVariable() String id, @RequestBody AdminChangePwdDto dto){
        Admin admin = adminService.getAdminByPwd(dto.getUsername(), dto.getPassword());
        if(admin == null)
            throw new RuntimeException("原始密码错误");
        if(!admin.getId().equals(id))
            throw new RuntimeException("用户id参数错误");
        if(!dto.confirmNewPassword())
            throw new RuntimeException("两次新密码输入不同");
        admin.setPassword(dto.getNewPassword());
        boolean flag = adminService.udpate(admin);
        return flag;
    }

}
