package com.yelp.business;

import evol.common.SearchParam;
import lombok.*;

@Data
public class BusinessSearchParam extends SearchParam {

    private String name;

    private String city;

    private String state;

    private String postCode;

    private boolean isOpen;
}
