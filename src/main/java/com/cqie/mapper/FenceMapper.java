package com.cqie.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqie.VO.FenceVo;
import com.cqie.VO.PenVo;
import com.cqie.entity.Fence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ljt
 * @description 针对表【fence】的数据库操作Mapper
 * @createDate 2023-12-20 09:24:49
 * @Entity org.cqie.entity.Fence
 */
public interface FenceMapper extends BaseMapper<Fence> {

    IPage<FenceVo> selectpage(IPage<FenceVo> page, @Param(Constants.WRAPPER) QueryWrapper<FenceVo> QueryWrapper);
}




