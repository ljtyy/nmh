package com.cqie.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountPageVo {
    private String account;

    private String password;

    private String accName;

    private String accPhone;

    private Integer accState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //分页参数
    private Integer pageNumber;
    private Integer pageSize;
}
