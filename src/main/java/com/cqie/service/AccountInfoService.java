package com.cqie.service;

import com.cqie.DTO.AccountInsertDto;
import com.cqie.DTO.LoginDto;
import com.cqie.VO.AccountPageVo;
import com.cqie.entity.AccountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqie.utils.Result;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ljt
 * @description 针对表【account_info】的数据库操作Service
 * @createDate 2023-12-20 09:43:06
 */
public interface AccountInfoService extends IService<AccountInfo> {

    Result login(LoginDto loginDto, HttpServletResponse response) throws Exception;

    Result current(String token);

    Result insert(AccountInsertDto accountInsertDto) throws Exception;

    Result selectpage(AccountPageVo accountPageVo);

    Result updateAcc(AccountInsertDto accountInsertDto) throws Exception;

    Result updateState(String account, Integer accState);
}
