package com.cqie.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DicVo {
    private Integer dicId;

    private String dicVal;

    private Integer dicState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    //分页参数
    private Integer pageNumber;
    private Integer pageSize;
}
