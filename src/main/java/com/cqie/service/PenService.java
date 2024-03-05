package com.cqie.service;

import com.cqie.DTO.PenDto;
import com.cqie.VO.PenVo;
import com.cqie.entity.Pen;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

/**
 * @author ljt
 * @description 针对表【pen】的数据库操作Service
 * @createDate 2023-12-20 09:24:49
 */
public interface PenService extends IService<Pen> {

    Result addpen(PenDto penDto);

    Result selectpage(PenVo penVo);

    Result updatepen(PenDto penDto);

    Result updateState(Integer penId, Integer penState);
}
