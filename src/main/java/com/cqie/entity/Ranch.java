package com.cqie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName ranch
 */
@TableName(value = "ranch")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ranch implements Serializable {
    @TableId(type = IdType.AUTO)
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
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}