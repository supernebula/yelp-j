package com.yelp.searchParam;

import evol.common.BaseSearchParam;
import lombok.*;

/**
 * 商家搜索参数类
 */
@Data
public final class BusinessSearchParam extends BaseSearchParam {

    private String name;

    private String city;

    private String state;

    private String postCode;

    private Boolean isOpen;
}
