package com.yelp.web.manage.controller.param.user;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class AdminUpdateDto {

    @NotEmpty(message = "用户id不能为空")
    private String id;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String mobile;
}
