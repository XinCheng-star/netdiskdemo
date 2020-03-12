package com.chengxin.netdiskdemo.service;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.UserBase;
import org.springframework.stereotype.Service;

/**
 * LogonService 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:52
 * @description 注册服务
 */
@Service
public interface LogonService {
    Result logon(UserBase userBase);
}
