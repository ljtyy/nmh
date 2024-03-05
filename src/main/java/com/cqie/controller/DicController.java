package com.cqie.controller;

import com.cqie.DTO.DicDto;
import com.cqie.VO.DicVo;
import com.cqie.service.DicService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api
@RequestMapping("/dic")
public class DicController {
    @Autowired
    private DicService dicService;

    @GetMapping("getAll")
    @ApiOperation("数据字典全部查询")
    public Result getAll() {
        Result r = dicService.getAll();
        return r;
    }

    @PostMapping("page")
    @ApiOperation("数据字典分页查询")
    public Result selectpage(@RequestBody DicVo dicVo) {
        Result r = dicService.selectpage(dicVo);
        return r;
    }

    @PostMapping("insert")
    @ApiOperation("数据字典的新增")
    @Validated
    public Result insert(@RequestBody @Valid DicDto dicDto) {
        Result r = dicService.insert(dicDto);
        return r;
    }

    @PutMapping("updateState/{dicId}/{dicState}")
    @ApiOperation("数据字典的状态修改")
    public Result updateState(@PathVariable Integer dicId, @PathVariable String dicState) {
        Result r = dicService.updateState(dicId, dicState);
        return r;
    }
}
