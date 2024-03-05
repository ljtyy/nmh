package com.cqie.controller;

import com.cqie.DTO.FenceDto;
import com.cqie.VO.FenceVo;
import com.cqie.service.FenceService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api
@RequestMapping("fence")
public class FenceController {
    @Autowired
    private FenceService fenceService;

    @PostMapping("insert")
    @ApiOperation("围栏新增")
    @Validated
    public Result insert(@RequestBody @Valid FenceDto fenceDto) {
        Result r = fenceService.insert(fenceDto);
        return r;
    }

    @PostMapping("page")
    @ApiOperation("围栏分页查询")
    public Result selectpage(@RequestBody FenceVo fenceVo) {
        Result r = fenceService.selectpage(fenceVo);
        return r;
    }

    @PutMapping("update")
    @ApiOperation("围栏的修改")
    @Validated
    public Result updatefence(@RequestBody @Valid FenceDto fenceDto) {
        Result r = fenceService.updatefence(fenceDto);
        return r;
    }

    @PutMapping("updateState/{fenceId}/{fenceState}")
    @ApiOperation("围栏的状态修改")
    public Result updateState(@PathVariable Integer fenceId, @PathVariable Integer fenceState) {
        Result r = fenceService.updateState(fenceId, fenceState);
        return r;
    }
}
