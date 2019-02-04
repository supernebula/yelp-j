package com.yelp.business;

import evol.common.BaseSearchParam;
import lombok.*;

@Data
public final class BusinessSearchParam extends BaseSearchParam {

    private String name;

    private String city;

    private String state;

    private String postCode;

    private boolean isOpen;
}
