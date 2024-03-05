package com.cqie.service;

import com.cqie.DTO.FenceDto;
import com.cqie.VO.FenceVo;
import com.cqie.entity.Fence;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

/**
 * @author ljt
 * @description 针对表【fence】的数据库操作Service
 * @createDate 2023-12-20 09:24:49
 */
public interface FenceService extends IService<Fence> {

    Result insert(FenceDto fenceDto);

    Result selectpage(FenceVo fenceVo);

    Result updatefence(FenceDto fenceDto);

    Result updateState(Integer fenceId, Integer fenceState);
}
