package com.yelp.business;

import evol.common.BaseSearchParam;
import lombok.Data;

import java.util.Date;

/**
 * 评论搜索参数类
 */
@Data
public class ReviewSearchParam extends BaseSearchParam {
    private Date startDate;
    private Date endDate;
    private String text;
    private Integer minUseful;
    private Integer maxUseful;
    private Integer minFunny;
    private Integer maxFunny;
    private Integer minCool;
    private Integer maxCool;
    private String businessId;
    private String userId;
}
