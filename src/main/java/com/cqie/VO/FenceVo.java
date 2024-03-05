package com.cqie.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FenceVo {
    @TableId
    private Integer fenceId;

    private String fenceName;

    private Integer fenceAmount;

    private Integer fenceState;

    private Integer ranchId;

    private Integer penId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //分页参数
    private Integer pageNumber;
    private Integer pageSize;

    private String ranchName;
    private String penName;
}
