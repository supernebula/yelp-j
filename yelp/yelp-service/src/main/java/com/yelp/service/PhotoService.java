package com.yelp.service;

import com.github.pagehelper.PageInfo;
import com.yelp.searchParam.PhotoSearchParam;
import com.yelp.entity.Photo;

public interface PhotoService {
    /**
     * 搜索照片
     * @param param 搜索条件
     * @return
     */
    PageInfo<Photo> Search(PhotoSearchParam param);

    /**
     * 根据id获取单单个照片
     * @param id
     * @return
     */
    Photo getPhoto(String id);
}
