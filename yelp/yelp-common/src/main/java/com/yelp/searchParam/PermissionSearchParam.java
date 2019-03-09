package com.yelp.searchParam;

import com.yelp.component.AvailableStatus;
import evol.common.BaseSearchParam;
import lombok.Data;

@Data
public class PermissionSearchParam  extends BaseSearchParam {

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private String parentId;

    private AvailableStatus available;

}
