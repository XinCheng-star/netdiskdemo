package com.chengxin.netdiskdemo.controller;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * LogoutController 的注释
 *
 * @author chengxin
 * @date 2020/3/12 10:37
 * @description
 */
@Controller
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout(HttpSession session) {

        //判断当前会话中是否有account字段
        if(session.getAttribute("account") == null){
            return new Result(false,StatusEnum.SESSION_PARAM_LOST,"SESSION_PARAM_LOST :: account",null);
        }

        //删除session的account，并且设置时间为1s
        session.removeAttribute("account");
        session.setMaxInactiveInterval(1);

        return new Result(true,StatusEnum.SUCCESS,"",null);
    }

}
