package com.chengxin.netdiskdemo.service;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.UserBase;
import org.springframework.stereotype.Service;

/**
 * LoginService 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:52
 * @description 登入服务
 */
@Service
public interface LoginService {
    Result login(UserBase userBase);
}
