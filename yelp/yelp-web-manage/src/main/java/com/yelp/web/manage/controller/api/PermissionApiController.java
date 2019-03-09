package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Permission;
import com.yelp.searchParam.PermissionSearchParam;
import com.yelp.service.PermissionService;
import com.yelp.web.manage.controller.param.permission.PermissionAvailableDto;
import com.yelp.web.manage.controller.param.permission.PermissionCreateDto;
import com.yelp.web.manage.controller.param.permission.PermissionUpdateDto;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

public class PermissionApiController {

    private PermissionService permissionService;

    @Autowired
    public PermissionApiController(PermissionService permissionService)
    {
        this.permissionService = permissionService;
    }

    private Permission ConvertDtoToPermission(PermissionCreateDto dto){
        Mapper mapper = new DozerBeanMapper();
        Permission permission = mapper.map(dto, Permission.class);
        return permission;
    }


    @ApiOperation(value = "搜索分页获取权限列表", notes = "")
    @GetMapping("search/{roleid}")
    public ApiResult<PageResult<Permission>> search(@PathVariable(name = "roleid") String roleId, PermissionSearchParam param){
        PageInfo<Permission> pageInfo = permissionService.Search(param);
        List<Permission> list = pageInfo.getList();
        PageResult<Permission> result = new PageResult<>(list, pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @ApiOperation(value = "获取权限信息", notes = "根据id获取权限信息")
    @GetMapping("detail/{id}")
    public ApiResult<Permission> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Permission permission = permissionService.getPermission(id);
        return ApiResult.success(permission);

    }

    @ApiOperation(value = "创建权限", notes = "")
    @PostMapping("create")
    public ApiResult<Object> createPost(PermissionCreateDto dto){

        Permission permission = ConvertDtoToPermission(dto);
        boolean flag = permissionService.insert(permission, dto.getRoleId());
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "编辑权限", notes = "")
    @PostMapping("edit/{id}")
    public ApiResult<Object> editPost(@PathVariable String id, PermissionUpdateDto dto){
        if(!id.equals(dto.getId()))
            throw new RuntimeException("权限id参数错误");
        Permission permission = permissionService.getPermission(dto.getId());
        if(permission == null)
            throw new RuntimeException("权限不存在");
        permission.setName(dto.getName());
        permission.setResourceType(dto.getResourceType());
        permission.setUrl(dto.getUrl());
        permission.setPermission(dto.getPermission());
        permission.setParentId(dto.getParentId());
        permission.setParentIds(dto.getParentIds());
        permission.setAvailable(dto.getAvailable().getCode());
        boolean flag = permissionService.udpate(permission);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "删除管理员", notes = "")
    @DeleteMapping("delete/{id}")
    public ApiResult<Object> delete(@PathVariable String id){
        boolean flag = permissionService.deleteById(id);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "修改权限有效性状态", notes = "")
    @PostMapping("changeValid/{id}")
    public ApiResult<Object> changeAvailable(String id, PermissionAvailableDto dto){
        if(!id.equals(dto.getId()))
            throw new RuntimeException("权限id参数错误");
        boolean flag = permissionService.changeAvailable(dto.getId(), dto.getAvailable());
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

}
