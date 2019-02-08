package com.yelp.business;

import evol.common.BaseSearchParam;
import lombok.Data;

import java.util.Date;

/**
 * 用户搜索参数类
 */
@Data
public class UserSearchParam extends BaseSearchParam {

    private String name;
    private Integer minReviewCount;
    private Integer MaxReviewCount;
    private Date yelpSinceStart;
    private Date yelpSinceEnd;
    private Integer minUseful;
    private Integer maxUseful;
    private Integer minFunny;
    private Integer maxFunny;
    private Integer minCool;
    private Integer maxCool;
    private Integer minFans;
    private Integer maxFans;
    private Float minAverageStars;
    private Float maxAverageStarts;
}

