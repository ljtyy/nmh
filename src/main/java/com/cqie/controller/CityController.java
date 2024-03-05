package com.cqie.controller;

import com.cqie.entity.City;
import com.cqie.service.CityService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("city")
@Api
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("province")
    @ApiOperation("省查询")
    public Result getProvince() {
        Result r = cityService.getProvince();
        return r;
    }
}
