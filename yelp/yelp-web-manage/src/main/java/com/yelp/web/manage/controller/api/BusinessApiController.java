package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.entity.Business;
import com.yelp.service.BusinessService;
import evol.common.PageResult;
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

    public PageResult<Business> search(BusinessSearchParam param){
        PageInfo<Business> pageInfo = businessService.Search(param);
        PageResult<Business> result = new PageResult<Business>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());

    }

    @GetMapping("detail/{id}")
    public void detail(@PathVariable String id){

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


