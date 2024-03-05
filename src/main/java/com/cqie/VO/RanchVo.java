package com.cqie.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RanchVo {
    @TableId
    private Integer ranchId;

    private String ranchName;

    private Integer ranchArea;

    private Integer ranchFeed;

    private String province;

    private String city;

    private String county;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer ranchAmount;

    private Integer ranchState;

    private Integer dicId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    //分页参数
    private Integer pageNumber = 1;
    private Integer pageSize = 10;
}
