package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.searchParam.PhotoSearchParam;
import com.yelp.entity.Photo;
import com.yelp.service.PhotoService;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/photo")
public class PhotoApiController {
    private PhotoService photoService;

    @Autowired
    public PhotoApiController(PhotoService service){
        this.photoService = service;
    }

    @GetMapping("search")
    public ApiResult<PageResult<Photo>> search(PhotoSearchParam param){
        PageInfo<Photo> pageInfo = photoService.Search(param);
        PageResult<Photo> result = new PageResult<>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<Photo> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Photo item = photoService.getPhoto(id);
        return ApiResult.success(item);
    }
}
