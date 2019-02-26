package com.yelp.web.manage.controller.result.admin;

import lombok.Data;

import java.util.Date;

@Data
public class AdminView {

    private String id;

    private String username;

    private String email;

    private String mobile;

    private Date createTime;

    private Date lastLoginTime;
}
