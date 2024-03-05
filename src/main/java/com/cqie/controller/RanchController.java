package com.cqie.controller;

import com.cqie.DTO.RanchDto;
import com.cqie.VO.RanchVo;
import com.cqie.entity.Ranch;
import com.cqie.service.RanchService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api
@RequestMapping("ranch")
public class RanchController {
    @Autowired
    private RanchService ranchService;

    @PostMapping("getRanch")
    @ApiOperation("牧场的新增")
    @Validated
    public Result getRanch(@RequestBody @Valid RanchDto ranchDto) {
        Result r = ranchService.getRanch(ranchDto);
        return r;
    }

    @PutMapping("updataRanch")
    @ApiOperation("牧场的修改")
    @Validated
    public Result updataRanch(@RequestBody @Valid RanchDto ranchDto) {
        Result r = ranchService.updataRanch(ranchDto);
        return r;
    }

    @PostMapping("page")
    @ApiOperation("牧场的分页查询")
    public Result selectpage(@RequestBody RanchVo ranchVo) {
        Result r = ranchService.selectpage(ranchVo);
        return r;
    }

    @PutMapping("/updataRanchState/{ranchId}/{ranchState}")
    @ApiOperation("牧场的状态修改")
    public Result updataRanchState(@PathVariable Integer ranchId, @PathVariable Integer ranchState) {
        Result r = ranchService.updataRanchState(ranchId, ranchState);
        return r;
    }
}
