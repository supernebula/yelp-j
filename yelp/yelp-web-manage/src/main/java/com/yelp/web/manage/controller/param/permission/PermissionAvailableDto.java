package com.yelp.web.manage.controller.param.permission;

import com.yelp.component.AvailableStatus;
import lombok.Data;

@Data
public class PermissionAvailableDto {

    private String id;

    private AvailableStatus available;
}
