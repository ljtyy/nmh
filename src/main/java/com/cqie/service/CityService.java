package com.cqie.service;

import com.cqie.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

/**
 * @author ljt
 * @description 针对表【city(基础表-省市区)】的数据库操作Service
 * @createDate 2023-12-21 09:15:45
 */
public interface CityService extends IService<City> {

    Result getProvince();
}
