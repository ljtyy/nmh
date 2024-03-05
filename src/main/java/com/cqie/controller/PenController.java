package com.cqie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqie.DTO.PenDto;
import com.cqie.VO.PenVo;
import com.cqie.service.PenService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api
@RequestMapping("pen")
public class PenController {

    @Autowired
    private PenService penService;

    @PostMapping("addpen")
    @ApiOperation("圈舍的新增")
    @Validated
    public Result addpen(@RequestBody @Valid PenDto penDto) {
        Result r = penService.addpen(penDto);
        return r;
    }

    @PostMapping("page")
    @ApiOperation("圈舍的分页查询")
    public Result selectpage(@RequestBody PenVo penVo) {
        Result r = penService.selectpage(penVo);
        return r;
    }

    @PutMapping("update")
    @ApiOperation("圈舍的修改")
    @Validated
    public Result updatepen(@RequestBody @Valid PenDto penDto) {
        Result r = penService.updatepen(penDto);
        return r;
    }

    @PutMapping("updateState/{penId}/{penState}")
    @ApiOperation("圈舍的状态修改")
    public Result updateState(@PathVariable Integer penId, @PathVariable Integer penState) {
        Result r = penService.updateState(penId, penState);
        return r;
    }

}
