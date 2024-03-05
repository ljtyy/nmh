package com.cqie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName city
 */
@TableName(value ="city")
@Data
public class City implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String code;

    private Integer level;

    private Integer parentId;

    private String acronym;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}