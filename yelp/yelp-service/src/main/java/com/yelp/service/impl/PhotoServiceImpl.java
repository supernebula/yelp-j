package com.yelp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yelp.business.PhotoSearchParam;
import com.yelp.dao.mapper.PhotoMapper;
import com.yelp.entity.Photo;
import com.yelp.entity.PhotoExample;
import com.yelp.service.PhotoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoMapper photoMapper;

    @Autowired
    public PhotoServiceImpl(PhotoMapper mapper){
        this.photoMapper = mapper;
    }

    public PageInfo<Photo> Search(PhotoSearchParam param) {
        if(param == null)
            throw new NullPointerException("param");

        PageHelper.startPage(param.getPage(), param.getPageSize());
        PhotoExample example = new PhotoExample();

        PhotoExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getBusinessId())) {criteria.andBusinessIdEqualTo(param.getBusinessId());}
        if(!StringUtils.isEmpty(param.getCaption())) {criteria.andCaptionLike(param.getCaption());}
        if(!StringUtils.isEmpty(param.getLabel())) {criteria.andLabelEqualTo(param.getLabel());}

        PageHelper.startPage(param.getPage(), param.getPageSize());
        List<Photo> list = photoMapper.selectByExample(example);
        PageInfo<Photo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Photo getPhoto(String id) {
        Photo item = photoMapper.selectByPrimaryKey(id);
        return item;
    }
}
