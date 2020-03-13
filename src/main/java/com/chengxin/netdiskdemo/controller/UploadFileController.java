package com.chengxin.netdiskdemo.controller;

import com.chengxin.netdiskdemo.model.FilesBase;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.service.UploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * UploadFileController 的注释
 *
 * @author chengxin
 * @date 2020/3/13 10:16
 * @description
 */
@Controller
public class UploadFileController {

    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/checkFiles", method = RequestMethod.POST)
    @ResponseBody
    public Result checkFile(@RequestBody @Valid FilesBase filesBase,
                            BindingResult bindingResult,
                            HttpSession session) {

        if (bindingResult.hasErrors()) {
            return new Result(false, StatusEnum.PARAM_ERROR, "PARAM_ERROR", bindingResult.getAllErrors());
        }

        //从session获得account信息
        String account = (String) session.getAttribute("account");
        //判断account 是否失效
        if (StringUtils.isBlank(account)) {
            return new Result(false, StatusEnum.NOT_FOUND, "account in session NOT_FOUND", null);
        }

        //去校验文件
        return uploadFileService.checkFile(filesBase, account);

    }


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile,
                             @RequestParam("checkCode") String checkCode,
                             @RequestParam("fileFolder") String fileFolder,
                             HttpSession session) throws IOException {

        if (multipartFile == null) {
            return new Result(false, StatusEnum.MULTI_FILE_NOT_GET, "MULTI_FILE_NOT_GET", null);
        }

        if (StringUtils.isBlank(checkCode)) {
            return new Result(false, StatusEnum.FILE_CHECK_CODE_BLANK, "FILE_CHECK_CODE_BLANK", null);
        }

        if (StringUtils.isBlank(fileFolder)) {
            return new Result(false, StatusEnum.FILE_LOGIC_PATH_BLANK, "FILE_LOGIC_PATH_BLANK", null);
        }

        String account = (String) session.getAttribute("account");
        if (StringUtils.isBlank(account)) {
            return new Result(false, StatusEnum.NOT_FOUND, "account in session NOT_FOUND", null);
        }

        return uploadFileService.uploadFile(multipartFile, checkCode, fileFolder, account);

    }


}
