package com.chengxin.netdiskdemo.controller;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.model.UserBase;
import com.chengxin.netdiskdemo.service.LoginService;
import com.chengxin.netdiskdemo.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * LoginController 的注释
 *
 * @author chengxin
 * @date 2020/3/12 9:25
 * @description 登陆页面的管理
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody @Valid UserBase userBase,
                        BindingResult bindingResult,
                        HttpServletResponse response,
                        HttpSession session) {

        if(bindingResult.hasErrors()){
            return new Result(false,StatusEnum.PARAM_ERROR,"PARAM_ERROR",bindingResult.getAllErrors());
        }

        //查看session是否有account
        if(session.getAttribute("account") == null){
            //还未登陆
            Result result = loginService.login(userBase);

            if(result.isSuccess()){

                //session 中添加已登陆的账号信息
                session.setAttribute("account",userBase.getAccount());
                session.setMaxInactiveInterval((int) TimeUtil.getHours(4).toSeconds());
                //response 中设置cookies
                response.addCookie(new Cookie("account",userBase.getAccount()));
            }

            return result;
        }else{
            //存在账号已经登陆
            return new Result(false,StatusEnum.DOUBLE_LOG_IN,"DOUBLE_LOG_IN",null);
        }
    }
}


