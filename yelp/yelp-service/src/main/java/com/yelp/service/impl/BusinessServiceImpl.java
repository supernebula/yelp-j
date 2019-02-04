package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.business.BusinessSearchParam;
import com.yelp.dao.mapper.BusinessMapper;
import com.yelp.entity.Business;
import com.yelp.entity.BusinessExample;
import com.yelp.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    private BusinessMapper businessMapper;

    @Autowired
    public BusinessServiceImpl(BusinessMapper mapper){
        this.businessMapper = mapper;
    }

    public PageInfo<Business> Search(BusinessSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        BusinessExample example = new BusinessExample();

        BusinessExample.Criteria criteria = example.or();
        if(!StringUtils.isEmpty(param.getName())) {criteria.andNameLike(param.getName());}

        if(!StringUtils.isEmpty(param.getCity())) {criteria.andCityEqualTo(param.getCity());}

        if(!StringUtils.isEmpty(param.getState())) {criteria.andStateEqualTo(param.getState());}

        if(!StringUtils.isEmpty(param.getPostCode())) {criteria.andPostalCodeEqualTo(param.getPostCode());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Business> list = businessMapper.selectByExample(example);
        PageInfo<Business> pageInfo = new PageInfo<Business>(list);

        return pageInfo;


    }

    public Business getBusiness(String id) {
        Business item = businessMapper.selectByPrimaryKey(id);
        return item;
    }
}
