package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Role;
import com.yelp.searchParam.RoleSearchParam;
import com.yelp.service.RoleService;
import com.yelp.web.manage.controller.param.role.RoleAvailableDto;
import com.yelp.web.manage.controller.param.role.RoleCreateDto;
import com.yelp.web.manage.controller.param.role.RoleUpdateDto;
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

public class RoleApiController {

    private RoleService roleService;

    @Autowired
    public RoleApiController(RoleService roleService)
    {
        this.roleService = roleService;
    }


    private Role ConvertDtoToRole(RoleCreateDto dto){
        Mapper mapper = new DozerBeanMapper();
        Role role = mapper.map(dto, Role.class);
        return role;
    }

    @ApiOperation(value = "搜索分页获取权限列表", notes = "")
    @GetMapping("search")
    public ApiResult<PageResult<Role>> search(RoleSearchParam param){
        PageInfo<Role> pageInfo = roleService.Search(param);

        List<Role> roles = pageInfo.getList();
        PageResult<Role> result = new PageResult<>(roles, pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @ApiOperation(value = "获取权限信息", notes = "根据id获取权限信息")
    @GetMapping("detail/{id}")
    public ApiResult<Role> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Role role = roleService.getRole(id);
        return ApiResult.success(role);

    }

    @ApiOperation(value = "创建角色", notes = "")
    @PostMapping("create")
    public ApiResult<Object> createPost(RoleCreateDto dto){
        Role role = ConvertDtoToRole(dto);
        boolean flag = roleService.insert(role);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "编辑角色", notes = "")
    @PostMapping("edit/{id}")
    public ApiResult<Object> editPost(@PathVariable String id, RoleUpdateDto dto){
        if(!id.equals(dto.getId()))
            throw new RuntimeException("用户id参数错误");
        Role role = roleService.getRole(dto.getId());
        if(role == null)
            throw new RuntimeException("角色不存在");
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setAvailable(dto.getAvailable().getCode());
        boolean flag = roleService.udpate(role);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "删除管理员", notes = "")
    @DeleteMapping("delete/{id}")
    public ApiResult<Object> delete(@PathVariable String id){
        boolean flag = roleService.deleteById(id);
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

    @ApiOperation(value = "修改角色有效性状态", notes = "")
    @PostMapping("changeValid/{id}")
    public ApiResult<Object> changeAvailable(String id, RoleAvailableDto dto){
        if(!id.equals(dto.getId()))
            throw new RuntimeException("角色id参数错误");
        boolean flag = roleService.changeAvailable(dto.getId(), dto.getAvailable());
        return flag ? ApiResult.success(null) : ApiResult.paramError();
    }

}
