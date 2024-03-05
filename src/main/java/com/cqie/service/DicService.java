package com.cqie.service;

import com.cqie.DTO.DicDto;
import com.cqie.VO.DicVo;
import com.cqie.entity.Dic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

/**
 * @author ljt
 * @description 针对表【dic】的数据库操作Service
 * @createDate 2023-12-20 09:24:49
 */
public interface DicService extends IService<Dic> {

    Result getAll();

    Result selectpage(DicVo dicVo);

    Result insert(DicDto dicDto);

    Result updateState(Integer dicId, String dicState);
}
