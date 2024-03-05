package com.cqie.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PenDto {
    @TableId(type = IdType.AUTO)
    private Integer penId;

    @NotEmpty(message = "圈舍名称不能为空")
    @Length(max = 50, message = "圈舍名称最长不能超过50")
    private String penName;

    private Integer penAmount;

    private Integer penState;
    
    private Integer ranchId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
