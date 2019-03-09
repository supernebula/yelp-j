package com.yelp.web.manage.controller.param.permission;

import com.yelp.component.AvailableStatus;
import lombok.Data;

@Data
public class PermissionCreateDto {

    private String roleId;

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private Integer parentId;

    private String parentIds;

    private AvailableStatus available;
}
