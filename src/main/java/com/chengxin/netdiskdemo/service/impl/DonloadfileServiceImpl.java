package com.chengxin.netdiskdemo.service.impl;

import com.chengxin.netdiskdemo.dao.FileTableMapper;
import com.chengxin.netdiskdemo.dao.UserFileTableMapper;
import com.chengxin.netdiskdemo.dataobject.FileTable;
import com.chengxin.netdiskdemo.dataobject.UserFileTable;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.service.DonloadfileService;
import com.chengxin.netdiskdemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DonloadfileServiceImpl 的注释
 *
 * @author chengxin
 * @date 2020/3/13 20:48
 * @description
 */
@Component
public class DonloadfileServiceImpl implements DonloadfileService {

    @Autowired
    UserFileTableMapper userFileTableMapper;

    @Autowired
    FileTableMapper fileTableMapper;

    @Override
    public Result downloadFile(String filePath,
                               String account,
                               HttpServletResponse response,
                               HttpServletRequest request) {

        String fileName = FileUtil.getFileNameFromPath(filePath);
        String folderName = FileUtil.getFileFolderFromPath(filePath);

        UserFileTable userFile = userFileTableMapper.selectByFolderFileName(folderName, fileName);
        //没有匹配的文件
        if (userFile == null) {
            return new Result(false, StatusEnum.NOT_FOUND, "download file is NOT_FOUND", null);
        }

        //获得checkCode
        FileTable fileTable = fileTableMapper.selectByCheckCode(userFile.getFileCheckCode());
        if (fileTable == null) {
            return new Result(false, StatusEnum.NOT_FOUND, "server files NOT_FOUND: file is not alive", null);
        }

        //开始处理文件传输
        String serverLocalPath = fileTable.getLocalPath();

        return FileUtil.downloadFileHelper(userFile, serverLocalPath, response, request);
    }
}
