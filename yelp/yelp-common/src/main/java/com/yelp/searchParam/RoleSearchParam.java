package com.yelp.searchParam;

import com.yelp.component.AvailableStatus;
import evol.common.BaseSearchParam;
import lombok.Data;

@Data
public class RoleSearchParam extends BaseSearchParam {

    private String name;

    private AvailableStatus available;
}
