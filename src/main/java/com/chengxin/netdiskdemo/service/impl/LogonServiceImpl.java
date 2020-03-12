package com.chengxin.netdiskdemo.service.impl;

import com.chengxin.netdiskdemo.dao.UserTableMapper;
import com.chengxin.netdiskdemo.dataobject.UserTable;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.model.UserBase;
import com.chengxin.netdiskdemo.model.UserStatusEnum;
import com.chengxin.netdiskdemo.service.LogonService;
import com.chengxin.netdiskdemo.util.IDUtil;
import com.chengxin.netdiskdemo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LogonServiceImpl 的注释
 *
 * @author chengxin
 * @date 2020/3/12 11:39
 * @description
 */
@Component
public class LogonServiceImpl implements LogonService {

    @Autowired
    UserTableMapper userTableMapper;

    @Override
    public Result logon(UserBase userBase) {

        UserTable userTable = userTableMapper.getUserByAccount(userBase.getAccount());
        if(userTable != null){
            return new Result(false, StatusEnum.DOUBLE_LOG_ON,"DOUBLE_LOG_ON",userBase);
        }

        //插入新user的信息
        UserTable user = new UserTable();
        user.setId(IDUtil.getId());
        user.setAccount(userBase.getAccount());
        user.setIsActive(UserStatusEnum.ALIVE.value());
        user.setPassword(MD5Util.getMd5FromPwd(userBase.getPwd()));

        if( userTableMapper.insert(user) > 0){
            return new Result(true,StatusEnum.SUCCESS,"",null);
        }
        return new Result(false,StatusEnum.INSERT_ERROR,"INSERT_ERROR",null);

    }

}
