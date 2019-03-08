package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.searchParam.UserSearchParam;
import com.yelp.entity.User;
import com.yelp.service.UserService;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private UserService userService;

    @Autowired
    public UserApiController(UserService service){
        this.userService = service;
    }

    @GetMapping("search")
    public ApiResult<PageResult<User>> search(UserSearchParam param, BindingResult bindingResult){
        PageInfo<User> pageInfo = userService.Search(param);
        PageResult<User> result = new PageResult<>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<User> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        User item = userService.getUser(id);
        return ApiResult.success(item);
    }
}