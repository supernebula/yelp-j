package com.yelp.web.manage.controller.param.admin;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class AdminCreateDto {

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 6, max = 15, message = "用户名长度必须大于 6 且小于 15 字符")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须大于 6 且小于 20 字符")
    private String password;

    @NotEmpty(message = "重复密码不能为空")
    @Size(min = 6, max = 20, message = "重复密码长度必须大于 6 且小于 20 字符")
    private String confirmPassword;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String mobile;


}
