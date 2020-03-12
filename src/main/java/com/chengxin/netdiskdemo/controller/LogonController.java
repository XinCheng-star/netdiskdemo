package com.chengxin.netdiskdemo.controller;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.model.UserBase;
import com.chengxin.netdiskdemo.service.LogonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * LogonController 的注释
 *
 * @author chengxin
 * @date 2020/3/12 10:31
 * @description
 */
@Controller
public class LogonController {

    @Autowired
    LogonService logonService;

    @RequestMapping(path = "/logon", method = RequestMethod.POST)
    @ResponseBody
    public Result logon(@RequestBody @Valid UserBase userBase,
                        BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new Result(false, StatusEnum.PARAM_ERROR,"PARAM_ERROR",bindingResult.getAllErrors());
        }

        return logonService.logon(userBase);
    }
}
