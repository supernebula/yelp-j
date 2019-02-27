package com.yelp.web.manage.controller.param.admin;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class AdminChangePwdDto {

    @NotEmpty()
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须大于 6 且小于 20 字符")
    private String password;

    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "新密码长度必须大于 6 且小于 20 字符")
    private String newPassword;

    @NotEmpty(message = "验证新密码不能为空")
    @Size(min = 6, max = 20, message = "验证新密码长度必须大于 6 且小于 20 字符")
    private String confirmPassword;
}
