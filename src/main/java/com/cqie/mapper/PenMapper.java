package com.cqie.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cqie.VO.PenVo;
import com.cqie.entity.Pen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ljt
 * @description 针对表【pen】的数据库操作Mapper
 * @createDate 2023-12-20 09:24:49
 * @Entity org.cqie.entity.Pen
 */
public interface PenMapper extends BaseMapper<Pen> {
    IPage<PenVo> selectPage(IPage<PenVo> page, @Param(Constants.WRAPPER) QueryWrapper<PenVo> QueryWrapper);
}




