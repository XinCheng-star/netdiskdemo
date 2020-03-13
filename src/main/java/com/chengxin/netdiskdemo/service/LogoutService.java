package com.chengxin.netdiskdemo.service;

import com.chengxin.netdiskdemo.model.Result;
import org.springframework.stereotype.Service;

/**
 * LogoutService 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:52
 * @description 登出服务
 */
@Service
public interface LogoutService {

    Result logout(String name);

}
