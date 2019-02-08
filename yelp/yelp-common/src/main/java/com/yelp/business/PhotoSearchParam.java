package com.yelp.business;

import evol.common.BaseSearchParam;
import lombok.*;

/**
 * 照片搜索参数类
 */
@Data
public class PhotoSearchParam extends BaseSearchParam {
    private String businessId;
    private String caption;
    private String label;
}
