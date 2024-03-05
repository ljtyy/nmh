package com.cqie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqie.DTO.AccountInsertDto;
import com.cqie.DTO.LoginDto;
import com.cqie.VO.AccountPageVo;
import com.cqie.entity.AccountInfo;
import com.cqie.service.AccountInfoService;
import com.cqie.mapper.AccountInfoMapper;
import com.cqie.utils.JwtTokenUtil;
import com.cqie.utils.Md5Util;
import com.cqie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljt
 * @description 针对表【account_info】的数据库操作Service实现
 * @createDate 2023-12-20 09:43:06
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo>
        implements AccountInfoService {
    @Autowired
    private AccountInfoMapper accountInfoMapper;

    LocalDateTime localTime = LocalDateTime.now();

    @Override
    public Result login(LoginDto loginDto, HttpServletResponse response) throws Exception {
        Md5Util md5Util = new Md5Util();
        String md5Pas = md5Util.md5(loginDto.getPassword(), "asdfghjkl");
        loginDto.setPassword(md5Pas);
        LambdaQueryWrapper<AccountInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AccountInfo::getAccount, loginDto.getAccount())
                .eq(AccountInfo::getPassword, loginDto.getPassword());
        AccountInfo accountInfo = accountInfoMapper.selectOne(lambdaQueryWrapper);
        if (accountInfo != null) {
            if (accountInfo.getAccState() == 1) {
                String token = JwtTokenUtil.buildJwt(loginDto.getAccount(), loginDto.getPassword());
                if (token != null) {
                    response.addHeader("Authorization", token);
                    response.setContentType(("application/json;charset=utf-8"));
                    Map date = new HashMap<>();
                    date.put("token", token);
                    return Result.Success(date);
                } else {
                    return Result.Failed(null, 0, "token已经过期");
                }
            } else {
                return Result.Failed(null, 0, "此账号已被禁用");
            }

        } else {
            return Result.Failed(null, 0, "登录失败，账号或密码错误");
        }
    }

    @Override
    public Result current(String token) {
        String username = JwtTokenUtil.getUsername(token);
        LambdaQueryWrapper<AccountInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AccountInfo::getAccount, username);
        AccountInfo accountInfo = accountInfoMapper.selectOne(lambdaQueryWrapper);
        accountInfo.setPassword("");
        return Result.Success(accountInfo);
    }

    @Override
    public Result insert(AccountInsertDto accountInsertDto) throws Exception {
        LambdaQueryWrapper<AccountInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AccountInfo::getAccPhone, accountInsertDto.getAccPhone());
        AccountInfo accountInfo = accountInfoMapper.selectOne(lambdaQueryWrapper);
        if (accountInfo == null) {
            Long count = accountInfoMapper.selectCount(null);
            Md5Util md5Util = new Md5Util();
            String pws = md5Util.md5(accountInsertDto.generateDefaultPassword(accountInsertDto.getAccPhone()), "asdfghjkl");
            AccountInfo accountInfo1 = new AccountInfo();
            accountInfo1.setAccount(accountInsertDto.generateLoginAccount(count + 1));
            accountInfo1.setPassword(pws);
            accountInfo1.setAccName(accountInsertDto.getAccName());
            accountInfo1.setAccPhone(accountInsertDto.getAccPhone());
            accountInfo1.setAccState(accountInsertDto.getAccState());
            accountInfo1.setCreateTime(localTime);
            accountInfo1.setUpdateTime(localTime);
            int insert = accountInfoMapper.insert(accountInfo1);
            if (insert != 0) {
                return Result.Success(insert);
            } else {
                return Result.Failed(null, 0, "新增失败");
            }
        } else {
            return Result.Failed(null, 0, "此手机号已被注册");
        }
    }

    @Override
    public Result selectpage(AccountPageVo accountPageVo) {
        Page<AccountInfo> page = new Page<>(accountPageVo.getPageNumber(), accountPageVo.getPageSize());
        LambdaQueryWrapper<AccountInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(accountPageVo.getAccName()), AccountInfo::getAccName, accountPageVo.getAccName())
                .like(StringUtils.isNotEmpty(accountPageVo.getAccPhone()), AccountInfo::getAccPhone, accountPageVo.getAccPhone())
                .eq(accountPageVo.getAccState() != null, AccountInfo::getAccState, accountPageVo.getAccState());
        Page<AccountInfo> accountInfoPage = accountInfoMapper.selectPage(page, lambdaQueryWrapper);
        return Result.Success(accountInfoPage);
    }

    @Override
    public Result updateAcc(AccountInsertDto accountInsertDto) throws Exception {
        LambdaQueryWrapper<AccountInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AccountInfo::getAccPhone, accountInsertDto.getAccPhone());
        AccountInfo accountInfo = accountInfoMapper.selectOne(lambdaQueryWrapper);
        if (accountInfo == null || accountInfo.getAccount().equals(accountInsertDto.getAccount())) {
            LambdaUpdateWrapper<AccountInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            accountInsertDto.setUpdateTime(localTime);
            Md5Util md5Util = new Md5Util();
            String pws = md5Util.md5(accountInsertDto.generateDefaultPassword(accountInsertDto.getAccPhone()), "asdfghjkl");
            lambdaUpdateWrapper.eq(AccountInfo::getAccount, accountInsertDto.getAccount())
                    .set(AccountInfo::getPassword, pws)
                    .set(AccountInfo::getAccName, accountInsertDto.getAccName())
                    .set(AccountInfo::getAccPhone, accountInsertDto.getAccPhone())
                    .set(AccountInfo::getAccState, accountInsertDto.getAccState())
                    .set(AccountInfo::getUpdateTime, accountInsertDto.getUpdateTime());
            int update = accountInfoMapper.update(null, lambdaUpdateWrapper);
            if (update != 0) {
                return Result.Success(update);
            } else {
                return Result.Failed(null, 0, "修改失败");
            }
        } else {
            return Result.Failed(null, 0, "此手机号已被注册");
        }
    }

    @Override
    public Result updateState(String account, Integer accState) {
        LambdaUpdateWrapper<AccountInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(AccountInfo::getAccount, account)
                .set(AccountInfo::getAccState, accState);
        int update = accountInfoMapper.update(null, lambdaUpdateWrapper);
        if (update != 0) {
            return Result.Success(update);
        } else {
            return Result.Failed(null, 0, "修改失败");
        }
    }
}




