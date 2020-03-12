package com.chengxin.netdiskdemo.service.impl;

import com.chengxin.netdiskdemo.dao.UserTableMapper;
import com.chengxin.netdiskdemo.dataobject.UserTable;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.model.UserBase;
import com.chengxin.netdiskdemo.service.LoginService;
import com.chengxin.netdiskdemo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * LoginServiceImpl 的注释
 *
 * @author chengxin
 * @date 2020/3/12 11:39
 * @description
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserTableMapper userTableMapper;

    @Override
    public Result login(UserBase userBase) {

        UserTable userTable = userTableMapper.getUserByAccount(userBase.getAccount());

        if (userTable == null) {
            return new Result(false, StatusEnum.NOT_LOG_ON, "USER_NOT_LOG_ON", null);
        }

        if (!Boolean.valueOf(userTable.getIsActive())) {
            return new Result(false, StatusEnum.USER_NOT_VALID, "USER_NOT_VALID", null);
        }

        String md5Code = MD5Util.getMd5FromPwd(userBase.getPwd());
        //判断密码是否正确
        if (!userTable.getPassword().equals(md5Code)) {
            return new Result(false, StatusEnum.WRONG_PWD, "WRONG_PWD", null);
        }

        userBase.setPwd(null);
        return new Result(true, StatusEnum.SUCCESS, "", userBase);

    }
}
