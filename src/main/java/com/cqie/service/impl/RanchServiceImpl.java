package com.cqie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.DTO.RanchDto;
import com.cqie.VO.RanchVo;
import com.cqie.entity.Ranch;
import com.cqie.service.RanchService;
import com.cqie.mapper.RanchMapper;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ljt
 * @description 针对表【ranch】的数据库操作Service实现
 * @createDate 2023-12-20 09:24:49
 */
@Service
public class RanchServiceImpl extends ServiceImpl<RanchMapper, Ranch>
        implements RanchService {
    @Autowired
    private RanchMapper ranchMapper;

    LocalDateTime localTime = LocalDateTime.now();

    @Override
    public Result getRanch(RanchDto ranchDto) {
        LambdaQueryWrapper<Ranch> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Ranch::getRanchName, ranchDto.getRanchName());
        Ranch ranch = ranchMapper.selectOne(lambdaQueryWrapper);
        if (ranch == null) {
            Ranch ranch1 = new Ranch();
            ranch1.setRanchName(ranchDto.getRanchName());
            ranch1.setRanchArea(ranchDto.getRanchArea());
            ranch1.setRanchFeed(ranchDto.getRanchFeed());
            ranch1.setProvince(ranchDto.getProvince());
            ranch1.setCity(ranchDto.getCity());
            ranch1.setCounty(ranchDto.getCounty());
            ranch1.setAddress(ranchDto.getAddress());
            ranch1.setLongitude(ranchDto.getLongitude());
            ranch1.setLatitude(ranchDto.getLatitude());
            ranch1.setRanchAmount(ranchDto.getRanchAmount());
            ranch1.setDicId(ranchDto.getDicId());
            ranch1.setCreateTime(localTime);
            ranch1.setUpdateTime(localTime);
            int insert = ranchMapper.insert(ranch1);
            return Result.Success(insert);
        } else {
            return Result.Failed(null, 0, "此牧场牧场已存在");
        }
    }

    @Override
    public Result updataRanch(RanchDto ranchDto) {
        LambdaQueryWrapper<Ranch> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Ranch::getRanchName, ranchDto.getRanchName());
        Ranch ranch = ranchMapper.selectOne(lambdaQueryWrapper);
        if (ranch == null || ranch.getRanchName().equals(ranchDto.getRanchName())) {
            LambdaUpdateWrapper<Ranch> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Ranch::getRanchId, ranchDto.getRanchId())
                    .set(Ranch::getRanchName, ranchDto.getRanchName())
                    .set(Ranch::getRanchArea, ranchDto.getRanchArea())
                    .set(Ranch::getRanchFeed, ranchDto.getRanchFeed())
                    .set(Ranch::getProvince, ranchDto.getProvince())
                    .set(Ranch::getCity, ranchDto.getCity())
                    .set(Ranch::getCounty, ranchDto.getCounty())
                    .set(Ranch::getAddress, ranchDto.getAddress())
                    .set(Ranch::getLongitude, ranchDto.getLongitude())
                    .set(Ranch::getLatitude, ranchDto.getLatitude())
                    .set(Ranch::getDicId, ranchDto.getDicId())
                    .set(Ranch::getUpdateTime, localTime);
            int update = ranchMapper.update(null, lambdaUpdateWrapper);
            if (update != 0) {
                return Result.Success(update);
            } else {
                return Result.Failed(null, 0, "修改失败");
            }
        } else {
            return Result.Failed(null, 0, "此牧场牧场已存在");
        }
    }

    @Override
    public Result selectpage(RanchVo ranchVo) {
        Page<Ranch> page = new Page<>(ranchVo.getPageNumber(), ranchVo.getPageSize());
        LambdaQueryWrapper<Ranch> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(ranchVo.getRanchName()), Ranch::getRanchName, ranchVo.getRanchName())
                .eq(ranchVo.getRanchState() != null, Ranch::getRanchState, ranchVo.getRanchState());
        Page<Ranch> ranchPage = ranchMapper.selectPage(page, lambdaQueryWrapper);
        if (ranchPage != null) {
            return Result.Success(ranchPage);
        }
        return Result.Failed(null, 0, "查询失败");
    }

    @Override
    public Result updataRanchState(Integer ranchId, Integer ranchState) {
        LambdaQueryWrapper<Ranch> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Ranch::getRanchId, ranchId).eq(Ranch::getRanchAmount, 0);
        Ranch ranch = ranchMapper.selectOne(lambdaQueryWrapper);
        if (ranch != null) {
            LambdaUpdateWrapper<Ranch> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Ranch::getRanchId, ranchId)
                    .set(Ranch::getRanchState, ranchState);
            int update = ranchMapper.update(null, lambdaUpdateWrapper);
            if (update != 0) {
                return Result.Success(update);
            } else {
                return Result.Failed(null, 0, "修改失败");
            }
        } else {
            return Result.Failed(null, 0, "状态修改失败,因为此牧场的存栏量不为0");
        }
    }
}




