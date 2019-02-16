package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.business.ReviewSearchParam;
import com.yelp.entity.Review;

import java.util.List;

public interface ReviewService {
    /**
     * 搜索评论
     * @param param 搜索条件
     * @return
     */
    PageInfo<Review> Search(ReviewSearchParam param);


    /**
     * 分页获取
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Review> getPageReview(int page, int pageSize);

    /**
     * 获取所有Review
     * @return
     */
    List<Review> getAllReview();


    /**
     * 根据id获取单条评论
     * @param id
     * @return
     */
    Review getReview(String id);
}
