package com.yelp.web.manage.controller.param.role;

import lombok.Data;

@Data
public class AdminRoleCreateDto {

    private String adminId;

    private String[] roleId;
}
