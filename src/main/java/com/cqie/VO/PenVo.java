package com.cqie.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cqie.entity.Ranch;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PenVo {
    @TableId(type = IdType.AUTO)
    private Integer penId;

    private String penName;

    private Integer penAmount;

    private Integer penState;

    private Integer ranchId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Ranch ranch;

    private String ranchName;

    //分页参数
    private Integer pageNumber = 1;
    private Integer pageSize = 10;
}
