package com.chengxin.netdiskdemo.controller;

import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.service.DonloadfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * DownloadFileController 的注释
 *
 * @author chengxin
 * @date 2020/3/13 20:12
 * @description
 */
@Controller
public class DownloadFileController {

    @Autowired
    DonloadfileService donloadfileService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public Result download(@RequestParam("filePath") String filePath,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           HttpSession session) {

        //从session中获得文件
        String account = (String) session.getAttribute("account");
        //判断用户是否登陆
        if (StringUtils.isBlank(account)) {
            return new Result(false, StatusEnum.NOT_FOUND, "account in session NOT_FOUND", null);
        }

        //下载路径为空白
        if (StringUtils.isBlank(filePath)) {
            return new Result(false, StatusEnum.FILENAME_IS_BLANK, "FILENAME_IS_BLANK", null);
        }

        //处理下载文件的业务
        return donloadfileService.downloadFile(filePath, account, response, request);
    }


}
