package com.yelp.user;

import evol.common.BaseSearchParam;
import lombok.Data;

@Data
public class AdminSearchParam extends BaseSearchParam {

    private String username;

    private String email;

    private String mobile;
}
