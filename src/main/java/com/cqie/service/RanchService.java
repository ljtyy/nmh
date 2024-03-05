package com.cqie.service;

import com.cqie.DTO.RanchDto;
import com.cqie.VO.RanchVo;
import com.cqie.entity.Ranch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

/**
 * @author ljt
 * @description 针对表【ranch】的数据库操作Service
 * @createDate 2023-12-20 09:24:49
 */
public interface RanchService extends IService<Ranch> {

    Result getRanch(RanchDto ranchDto);

    Result updataRanch(RanchDto ranchDto);

    Result selectpage(RanchVo ranchVo);

    Result updataRanchState(Integer ranchId, Integer ranchState);
}
