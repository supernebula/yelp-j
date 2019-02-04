package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Business;
import com.yelp.service.BusinessService;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import evol.common.api.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yelp.business.BusinessSearchParam;

@RestController
@RequestMapping("/api/business")
public class BusinessApiController {

    private BusinessService businessService;

    @Autowired
    public BusinessApiController(BusinessService service){
        this.businessService = service;
    }

    @GetMapping("search")
    public ApiResult<PageResult<Business>> search(BusinessSearchParam param){
        PageInfo<Business> pageInfo = businessService.Search(param);
        PageResult<Business> result = new PageResult<>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<Business> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Business item = businessService.getBusiness(id);
        return ApiResult.success(item);

    }

    @PostMapping("create")
    public void createPost(){

    }

    @PostMapping("edit")
    public void editPost(){

    }

    @DeleteMapping("delete")
    public void delete(){

    }
}


