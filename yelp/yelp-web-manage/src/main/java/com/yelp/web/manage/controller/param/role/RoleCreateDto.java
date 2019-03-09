package com.yelp.web.manage.controller.param.role;

import com.yelp.component.AvailableStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class RoleCreateDto {

    @NotEmpty(message = "角色不能为空")
    @Size(min = 6, max = 15, message = "角色长度必须大于 6 且小于 15 字符")
    private String name;

    private String description;

    private AvailableStatus available;
}
