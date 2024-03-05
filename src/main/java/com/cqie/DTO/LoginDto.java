package com.cqie.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LoginDto {
    @NotEmpty(message = "账号不能为空")
    @Length(max = 20,message = "账号最长为20位")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "用户名只能是数字和字母")
    private String account;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 20,message = "密码最长为20位")
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "用户名只能是数字和字母")
    private String password;
}
