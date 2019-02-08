package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.business.UserSearchParam;
import com.yelp.dao.mapper.UserMapper;
import com.yelp.entity.User;
import com.yelp.entity.UserExample;
import com.yelp.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper){
        this.userMapper = mapper;
    }

    public PageInfo<User> Search(UserSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(param.getName())) {criteria.andNameLike(param.getName());}

        if(param.getYelpSinceStart() != null && param.getYelpSinceEnd() != null) {
            criteria.andYelpingSinceBetween(param.getYelpSinceStart(), param.getYelpSinceEnd());
        } else if(param.getYelpSinceStart() != null) {
            criteria.andYelpingSinceGreaterThanOrEqualTo(param.getYelpSinceStart());
        } else if(param.getYelpSinceEnd() != null){
            criteria.andYelpingSinceLessThanOrEqualTo(param.getYelpSinceEnd());
        }

        if(param.getMinReviewCount() != null) {criteria.andReviewCountGreaterThanOrEqualTo(param.getMinReviewCount().intValue());}
        if(param.getMaxReviewCount() != null) {criteria.andReviewCountLessThanOrEqualTo(param.getMaxReviewCount().intValue());}


        if(param.getMinUseful() != null) {criteria.andUsefulGreaterThanOrEqualTo(param.getMinUseful().intValue());}
        if(param.getMaxUseful() != null) {criteria.andUsefulLessThanOrEqualTo(param.getMaxUseful().intValue());}

        if(param.getMinUseful() != null) {criteria.andUsefulGreaterThanOrEqualTo(param.getMinUseful().intValue());}
        if(param.getMaxUseful() != null) {criteria.andUsefulLessThanOrEqualTo(param.getMaxUseful().intValue());}

        if(param.getMinFunny() != null) {criteria.andFunnyGreaterThanOrEqualTo(param.getMinFunny().intValue());}
        if(param.getMaxFunny() != null) {criteria.andFunnyLessThanOrEqualTo(param.getMinFunny().intValue());}

        if(param.getMinCool() != null) {criteria.andCoolGreaterThanOrEqualTo(param.getMinCool().intValue());}
        if(param.getMaxCool() != null) {criteria.andUsefulLessThanOrEqualTo(param.getMaxCool().intValue());}

        if(param.getMinFans() != null) {criteria.andFansGreaterThanOrEqualTo(param.getMinFans().intValue());}
        if(param.getMaxFans() != null) {criteria.andFansLessThanOrEqualTo(param.getMaxFans().intValue());}

        if(param.getMinAverageStars() != null) {criteria.andAverageStarsGreaterThanOrEqualTo(param.getMinAverageStars().floatValue());}
        if(param.getMaxAverageStarts() != null) {criteria.andAverageStarsLessThanOrEqualTo(param.getMaxAverageStarts().floatValue());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<User> list = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public User getUser(String id) {
        User item = userMapper.selectByPrimaryKey(id);
        return item;
    }
}
