package com.cqie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.DTO.DicDto;
import com.cqie.VO.DicVo;
import com.cqie.entity.Dic;
import com.cqie.service.DicService;
import com.cqie.mapper.DicMapper;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ljt
 * @description 针对表【dic】的数据库操作Service实现
 * @createDate 2023-12-20 09:24:49
 */
@Service
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic>
        implements DicService {
    @Autowired
    private DicMapper dicMapper;

    LocalDateTime localTime = LocalDateTime.now();

    @Override
    public Result getAll() {
        List<Dic> list = lambdaQuery().eq(Dic::getDicState, 1).list();
        return Result.Success(list);
    }

    @Override
    public Result selectpage(DicVo dicVo) {
        Page<Dic> page = new Page<>(dicVo.getPageNumber(), dicVo.getPageSize());
        LambdaQueryWrapper<Dic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(dicVo.getDicVal()), Dic::getDicVal, dicVo.getDicVal())
                .like(dicVo.getDicState() != null, Dic::getDicState, dicVo.getDicState());
        Page<Dic> dicPage = dicMapper.selectPage(page, lambdaQueryWrapper);
        if (dicPage != null) {
            return Result.Success(dicPage);
        }
        return Result.Failed(null, 0, "查询失败");
    }

    @Override
    public Result insert(DicDto dicDto) {
        LambdaQueryWrapper<Dic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dic::getDicVal, dicDto.getDicVal());
        Dic one = dicMapper.selectOne(lambdaQueryWrapper);
        if (one == null) {
            Dic dic = new Dic();
            dic.setDicVal(dicDto.getDicVal());
            dic.setCreateTime(localTime);
            dic.setUpdateTime(localTime);
            int insert = dicMapper.insert(dic);
            return Result.Success(insert);
        }
        return Result.Failed(null, 0, "此名称已存在");
    }

    @Override
    public Result updateState(Integer dicId, String dicState) {
        LambdaUpdateWrapper<Dic> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Dic::getDicId, dicId)
                .set(Dic::getDicState, dicState)
                .set(Dic::getUpdateTime, localTime);
        int update = dicMapper.update(null, lambdaUpdateWrapper);
        if (update > 0) {
            return Result.Success(update);
        } else {
            return Result.Failed(null, 0, "状态修改失败");
        }
    }
}




