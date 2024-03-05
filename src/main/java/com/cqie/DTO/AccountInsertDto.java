package com.cqie.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AccountInsertDto {

    private String account;

    private String password;

    @NotEmpty(message = "人员姓名不能为空")
    @Length(max = 10, message = "人员姓名最大长度为10位")
    private String accName;

    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能位11位")
    private String accPhone;

    private Integer accState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 生成默认密码
    public String generateDefaultPassword(String accPhone) {
        return accPhone.substring(accPhone.length() - 6);
    }

    // 生成登录账号
    public String generateLoginAccount(Long count) {
        String sequence = String.format("%04d", count); // 序号格式化为4位数字，不足部分用0补齐
        return "YZ" + sequence;
    }
}
