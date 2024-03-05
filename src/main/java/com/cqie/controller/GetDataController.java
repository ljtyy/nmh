package com.cqie.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.cqie.entity.Pen;
import com.cqie.entity.Ranch;
import com.cqie.mapper.PenMapper;
import com.cqie.mapper.RanchMapper;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Api
@RequestMapping("")
public class GetDataController {
    @Autowired
    private RanchMapper ranchMapper;

    @Autowired
    private PenMapper penMapper;

    @GetMapping("")
    @ApiOperation("获取牧场和圈舍数据")
    public Result getDate(Model model) {
        List<Ranch> ranchList = ranchMapper.selectList(null);
        model.addAttribute("ranchDate", ranchList);
        List<Pen> penList = penMapper.selectList(null);
        model.addAttribute("panDate", penList);
        return Result.Success(model);
    }
}
