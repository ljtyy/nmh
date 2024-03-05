package com.cqie.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RanchDto {
    @TableId(type = IdType.AUTO)
    private Integer ranchId;

    @NotEmpty(message = "牧场牧场不能为空")
    @Length(max = 100, message = "牧场牧场最长不能超过100")
    private String ranchName;

    @NotNull(message = "场地规模不能为空")
    @Min(value = 100, message = "场地规模最小为100")
    @Max(value = 999999, message = "场地规模最大为999999")
    private Integer ranchArea;

    @NotNull(message = "养殖规模不能为空")
    @Max(value = 999999, message = "养殖规模最多为999999")
    private Integer ranchFeed;

    @NotEmpty(message = "省名不能为空")
    private String province;

    @NotEmpty(message = "市名不能为空")
    private String city;

    @NotEmpty(message = "区县名不能为空")
    private String county;

    @NotEmpty(message = "详细地址不能为空")
    private String address;

    @NotNull(message = "经度不能为空")
    @Max(value = 180, message = "经度不能大于180")
    @Min(value = -180, message = "经度不能小于-180")
    private BigDecimal longitude;

    @NotNull(message = "纬度不能为空")
    @Max(value = 90, message = "纬度不能大于90")
    @Min(value = -90, message = "纬度不能小于-90")
    private BigDecimal latitude;

    private Integer ranchAmount;

    private Integer ranchState;

    @NotNull(message = "牲畜品种不能为空")
    private Integer dicId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
