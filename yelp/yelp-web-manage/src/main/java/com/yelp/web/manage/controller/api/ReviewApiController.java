package com.yelp.web.manage.controller.api;

import com.github.pagehelper.PageInfo;
import com.yelp.business.ReviewSearchParam;
import com.yelp.entity.Review;
import com.yelp.service.ReviewService;
import evol.common.PageResult;
import evol.common.api.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewApiController {
    private ReviewService reviewService;

    @Autowired
    public ReviewApiController(ReviewService service){
        this.reviewService = service;
    }

    @GetMapping("search")
    public ApiResult<PageResult<Review>> search(ReviewSearchParam param){
        PageInfo<Review> pageInfo = reviewService.Search(param);
        PageResult<Review> result = new PageResult<>(pageInfo.getList(), pageInfo.getPages(), pageInfo.getPageSize(), pageInfo.getTotal() / pageInfo.getPageSize(), pageInfo.getTotal());
        return ApiResult.success(result);
    }

    @GetMapping("detail/{id}")
    public ApiResult<Review> detail(@PathVariable String id){
        if(StringUtils.isEmpty(id)) {
            return ApiResult.paramError();
        }
        Review item = reviewService.getReview(id);
        return ApiResult.success(item);
    }
}
