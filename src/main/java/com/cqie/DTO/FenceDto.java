package com.cqie.DTO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class FenceDto {
    @TableId
    private Integer fenceId;

    @NotEmpty(message = "围栏名称不能为空")
    @Length(max = 20, message = "围栏名称长度最长为20")
    private String fenceName;

    private Integer fenceAmount;

    private Integer fenceState;

    private Integer ranchId;

    private Integer penId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
