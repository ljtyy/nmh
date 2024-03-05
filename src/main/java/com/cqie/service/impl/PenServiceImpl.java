package com.cqie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.DTO.PenDto;
import com.cqie.VO.PenVo;
import com.cqie.entity.Pen;
import com.cqie.entity.Ranch;
import com.cqie.mapper.RanchMapper;
import com.cqie.service.PenService;
import com.cqie.mapper.PenMapper;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ljt
 * @description 针对表【pen】的数据库操作Service实现
 * @createDate 2023-12-20 09:24:49
 */
@Service
public class PenServiceImpl extends ServiceImpl<PenMapper, Pen>
        implements PenService {

    @Autowired
    private PenMapper penMapper;
    @Autowired
    private RanchMapper ranchMapper;


    LocalDateTime localTime = LocalDateTime.now();

    @Override
    public Result addpen(PenDto penDto) {
        //获取牧场信息，让新增/修改框拿到牧场的名称
//        List<Ranch> ranchList = ranchMapper.selectList(null);
        LambdaQueryWrapper<Pen> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pen::getPenName, penDto.getPenName())
                .eq(Pen::getRanchId, penDto.getRanchId());
        Pen pen = penMapper.selectOne(lambdaQueryWrapper);
        if (pen == null) {
            Pen pen1 = new Pen();
            pen1.setRanchId(penDto.getRanchId());
            pen1.setPenName(penDto.getPenName());
            pen1.setPenAmount(penDto.getPenAmount());
            pen1.setCreateTime(localTime);
            pen1.setUpdateTime(localTime);
            int insert = penMapper.insert(pen1);
            if (insert != 0) {
                return Result.Success(insert);
            } else {
                return Result.Failed(null, 0, "新增失败");
            }
        } else {
            return Result.Failed(null, 0, "此圈舍名已存在");
        }
    }

    @Override
    public Result selectpage(PenVo penVo) {
        Page<PenVo> page = new Page<>(penVo.getPageNumber(), penVo.getPageSize());
        QueryWrapper<PenVo> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(penVo.getRanchName()), "ranch.ranch_name", penVo.getRanchName())
                .like(StringUtils.isNotEmpty(penVo.getPenName()), "pen.pen_name", penVo.getPenName())
                .eq(penVo.getPenState() != null, "pen.pen_state", penVo.getPenState());
        IPage<PenVo> penVoIPage = penMapper.selectPage(page, wrapper);
        if (penVoIPage != null) {
            return Result.Success(penVoIPage);
        }
        return Result.Failed(null, 0, "查询失败");
    }

    @Override
    public Result updatepen(PenDto penDto) {
        LambdaQueryWrapper<Pen> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pen::getPenName, penDto.getPenName())
                .eq(Pen::getRanchId, penDto.getRanchId());
        Pen pen = penMapper.selectOne(lambdaQueryWrapper);
        if (pen == null) {
            LambdaUpdateWrapper<Pen> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Pen::getPenId, penDto.getPenId())
                    .set(Pen::getPenName, penDto.getPenName())
                    .set(Pen::getUpdateTime, localTime);
            int update = penMapper.update(null, lambdaUpdateWrapper);
            if (update != 0) {
                return Result.Success(update);
            }
            return Result.Failed(null, 0, "修改失败");
        }
        return Result.Failed(null, 0, "此圈舍名已存在");
    }

    @Override
    public Result updateState(Integer penId, Integer penState) {
        LambdaQueryWrapper<Pen> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pen::getPenId, penId)
                .eq(Pen::getPenAmount, 0);
        Pen pen = penMapper.selectOne(lambdaQueryWrapper);
        if (pen != null) {
            LambdaUpdateWrapper<Pen> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Pen::getPenId, penId)
                    .set(Pen::getPenState, penState);
            int update = penMapper.update(null, lambdaUpdateWrapper);
            if (update != 0) {
                return Result.Success(update);
            }
            return Result.Failed(null, 0, "修改失败");
        }
        return Result.Failed(null, 0, "修改失败，此圈舍中还有存栏，不能禁用");
    }
}




