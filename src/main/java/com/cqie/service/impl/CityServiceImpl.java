package com.cqie.service.impl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.VO.CityVo;
import com.cqie.entity.City;
import com.cqie.service.CityService;
import com.cqie.mapper.CityMapper;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljt
 * @description 针对表【city(基础表-省市区)】的数据库操作Service实现
 * @createDate 2023-12-21 09:15:45
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
        implements CityService {
    @Autowired
    private CityMapper cityMapper;

    private List<CityVo> buildCityTree(List<City> cities, Integer parentid) {
        List<CityVo> result = new ArrayList<>();
        for (City city : cities) {
            if (city.getParentId().equals(parentid)) {
                List<CityVo> children = buildCityTree(cities, city.getId().intValue());
                CityVo cityVo = CityVo.builder()
                        .id(city.getId())
                        .name(city.getName())
                        .acronym(city.getAcronym())
                        .sort(city.getSort())
                        .updateTime(city.getUpdateTime())
                        .level(city.getLevel())
                        .parentId(city.getParentId())
                        .createTime(city.getCreateTime())
                        .code(city.getCode())
                        .children(children)
                        .build();
                result.add(cityVo);
            }
        }
        return result;
    }

    @Override
    public Result getProvince() {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<>();
        List<City> cities = cityMapper.selectList(queryWrapper);
        List<CityVo> cityVo = buildCityTree(cities, 0);
        return Result.Success(cityVo);
    }

}




