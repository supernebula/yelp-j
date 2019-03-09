package com.yelp.web.manage.controller.param.role;

import com.yelp.component.AvailableStatus;
import lombok.Data;

@Data
public class RoleAvailableDto {

    private String id;

    private AvailableStatus available;
}
