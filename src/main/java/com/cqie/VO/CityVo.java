package com.cqie.VO;

import com.cqie.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityVo {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String code;

    private Integer level;

    private Integer parentId;

    private String acronym;

    private Integer sort;

    private List<CityVo> children;

    private static final long serialVersionUID = 1L;
}
