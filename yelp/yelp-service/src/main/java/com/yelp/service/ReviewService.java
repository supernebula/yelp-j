package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.business.ReviewSearchParam;
import com.yelp.entity.Review;

public interface ReviewService {
    /**
     * 搜索评论
     * @param param 搜索条件
     * @return
     */
    PageInfo<Review> Search(ReviewSearchParam param);

    /**
     * 根据id获取单条评论
     * @param id
     * @return
     */
    Review getReview(String id);
}
