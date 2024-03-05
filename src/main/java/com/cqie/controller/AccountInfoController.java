package com.cqie.controller;

import com.cqie.DTO.AccountInsertDto;
import com.cqie.DTO.LoginDto;
import com.cqie.VO.AccountPageVo;
import com.cqie.service.AccountInfoService;
import com.cqie.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api
@RequestMapping("/accountInfo")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    @PostMapping("login")
    @ApiOperation("用户登录")
    @Validated
    public Result login(@RequestBody @Valid LoginDto loginDto, HttpServletResponse response) throws Exception {
        Result r = accountInfoService.login(loginDto, response);
        return r;
    }

    @GetMapping("current")
    @ApiOperation("获取当前登录的用户信息")
    public Result current(@RequestHeader String token) {
        Result r = accountInfoService.current(token);
        return r;
    }

    @PostMapping("insert")
    @ApiOperation("新增人员")
    @Validated
    public Result insert(@RequestBody @Valid AccountInsertDto accountInsertDto) throws Exception {
        Result r = accountInfoService.insert(accountInsertDto);
        return r;
    }

    @PostMapping("page")
    @ApiOperation("分页查询人员信息")
    public Result selectpage(@RequestBody AccountPageVo accountPageVo) {
        Result r = accountInfoService.selectpage(accountPageVo);
        return r;
    }

    @PutMapping("update")
    @ApiOperation("修改人员信息")
    @Validated
    public Result updateAcc(@RequestBody @Valid AccountInsertDto accountInsertDto) throws Exception {
        Result r = accountInfoService.updateAcc(accountInsertDto);
        return r;
    }

    @PutMapping("/updateState/{account}/{accState}")
    @ApiOperation("修改人员状态")
    public Result updateState(@PathVariable String account, @PathVariable Integer accState) {
        Result r = accountInfoService.updateState(account, accState);
        return r;
    }

}
