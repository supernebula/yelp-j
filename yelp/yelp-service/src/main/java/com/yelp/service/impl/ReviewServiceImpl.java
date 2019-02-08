package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.business.ReviewSearchParam;
import com.yelp.dao.mapper.ReviewMapper;
import com.yelp.entity.Review;
import com.yelp.entity.ReviewExample;
import com.yelp.service.ReviewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper mapper){
        this.reviewMapper = mapper;
    }

    public PageInfo<Review> Search(ReviewSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        ReviewExample example = new ReviewExample();

        ReviewExample.Criteria criteria = example.createCriteria();
        if(param.getStartDate() != null && param.getEndDate() != null) {
            criteria.andDateBetween(param.getStartDate(), param.getEndDate());
        } else if(param.getStartDate() != null) {
            criteria.andDateGreaterThanOrEqualTo(param.getStartDate());
        } else if(param.getEndDate() != null){
            criteria.andDateLessThanOrEqualTo(param.getEndDate());
        }

        if(param.getMinUseful() != null) {criteria.andUsefulGreaterThanOrEqualTo(param.getMinUseful().intValue());}
        if(param.getMaxUseful() != null) {criteria.andUsefulLessThanOrEqualTo(param.getMaxUseful().intValue());}
        if(param.getMinFunny() != null) {criteria.andFunnyGreaterThanOrEqualTo(param.getMinFunny().intValue());}
        if(param.getMaxFunny() != null) {criteria.andFunnyLessThanOrEqualTo(param.getMinFunny().intValue());}
        if(param.getMinCool() != null) {criteria.andCoolGreaterThanOrEqualTo(param.getMinCool().intValue());}
        if(param.getMaxCool() != null) {criteria.andUsefulLessThanOrEqualTo(param.getMaxCool().intValue());}
        if(!StringUtils.isEmpty(param.getBusinessId())) {criteria.andBusinessIdEqualTo(param.getBusinessId());}
        if(!StringUtils.isEmpty(param.getUserId())) {criteria.andUserIdEqualTo(param.getUserId());}

        //if(!StringUtils.isEmpty(param.getText())) {criteria.andTextLike(param.getText());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Review> list = reviewMapper.selectByExample(example);
        PageInfo<Review> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Review getReview(String id) {
        Review item = reviewMapper.selectByPrimaryKey(id);
        return item;
    }

}
