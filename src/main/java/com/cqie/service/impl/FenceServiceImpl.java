package com.cqie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.DTO.FenceDto;
import com.cqie.VO.FenceVo;
import com.cqie.entity.Fence;
import com.cqie.service.FenceService;
import com.cqie.mapper.FenceMapper;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ljt
 * @description 针对表【fence】的数据库操作Service实现
 * @createDate 2023-12-20 09:24:49
 */
@Service
public class FenceServiceImpl extends ServiceImpl<FenceMapper, Fence>
        implements FenceService {
    @Autowired
    private FenceMapper fenceMapper;
    LocalDateTime localTime = LocalDateTime.now();

    @Override
    public Result insert(FenceDto fenceDto) {
        LambdaQueryWrapper<Fence> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Fence::getFenceName, fenceDto.getFenceName())
                .eq(Fence::getPenId, fenceDto.getPenId())
                .eq(Fence::getRanchId, fenceDto.getRanchId());
        Fence fence = fenceMapper.selectOne(lambdaQueryWrapper);
        if (fence == null) {
            Fence fence1 = new Fence();
            fence1.setRanchId(fenceDto.getRanchId());
            fence1.setPenId(fenceDto.getPenId());
            fence1.setFenceName(fenceDto.getFenceName());
            fence1.setCreateTime(localTime);
            fence1.setUpdateTime(localTime);
            int insert = fenceMapper.insert(fence1);
            return Result.Success(insert);
        }
        return Result.Failed(null, 0, "此围栏已存在");
    }

    @Override
    public Result selectpage(FenceVo fenceVo) {
        Page<FenceVo> page = new Page<>(fenceVo.getPageNumber(), fenceVo.getPageSize());
        QueryWrapper<FenceVo> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(fenceVo.getRanchName()), "ranch.ranch_name", fenceVo.getRanchName())
                .like(StringUtils.isNotEmpty(fenceVo.getPenName()), "pen.pen_name", fenceVo.getPenName())
                .like(StringUtils.isNotEmpty(fenceVo.getFenceName()), "fence.fence_name", fenceVo.getFenceName())
                .eq(fenceVo.getFenceState() != null, "fence.fence_state", fenceVo.getFenceState());
        IPage<FenceVo> selectpage = fenceMapper.selectpage(page, wrapper);
        return Result.Success(selectpage);
    }

    @Override
    public Result updatefence(FenceDto fenceDto) {
        LambdaQueryWrapper<Fence> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Fence::getFenceName, fenceDto.getFenceName())
                .eq(Fence::getPenId, fenceDto.getPenId())
                .eq(Fence::getRanchId, fenceDto.getRanchId());
        Fence fence = fenceMapper.selectOne(lambdaQueryWrapper);
        if (fence == null) {
            LambdaUpdateWrapper<Fence> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Fence::getFenceId, fenceDto.getFenceId())
                    .set(Fence::getPenId, fenceDto.getPenId())
                    .set(Fence::getFenceName, fenceDto.getFenceName())
                    .set(Fence::getUpdateTime, localTime);
            int update = fenceMapper.update(null, lambdaUpdateWrapper);
            return Result.Success(update);
        }
        return Result.Failed(null, 0, "此围栏已存在");
    }

    @Override
    public Result updateState(Integer fenceId, Integer fenceState) {
        LambdaQueryWrapper<Fence> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Fence::getFenceId, fenceId)
                .eq(Fence::getFenceAmount, 0);
        Fence fence = fenceMapper.selectOne(lambdaQueryWrapper);
        if (fence != null) {
            LambdaUpdateWrapper<Fence> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Fence::getFenceId, fenceId)
                    .set(Fence::getFenceState, fenceState);
            int update = fenceMapper.update(null, lambdaUpdateWrapper);
            return Result.Success(update);
        }
        return Result.Failed(null, 0, "修改失败，此围栏中还有存栏，不能禁用");
    }
}




