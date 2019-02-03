package com.yelp.web.manage.controller.api;

import evol.common.PageResult;
import org.springframework.web.bind.annotation.*;
import com.yelp.business.BusinessSearchParam;

@RestController
@RequestMapping("/api/business")
public class BusinessApiController {

    public void search(BusinessSearchParam param){

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


