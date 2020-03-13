package com.chengxin.netdiskdemo.service.impl;

import com.chengxin.netdiskdemo.dao.FileTableMapper;
import com.chengxin.netdiskdemo.dao.UserDiskCapacityTableMapper;
import com.chengxin.netdiskdemo.dao.UserFileTableMapper;
import com.chengxin.netdiskdemo.dataobject.FileTable;
import com.chengxin.netdiskdemo.dataobject.UserDiskCapacityTable;
import com.chengxin.netdiskdemo.dataobject.UserFileTable;
import com.chengxin.netdiskdemo.model.FileTypeEnum;
import com.chengxin.netdiskdemo.model.FilesBase;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import com.chengxin.netdiskdemo.model.UserTypeEnum;
import com.chengxin.netdiskdemo.service.UploadFileService;
import com.chengxin.netdiskdemo.util.FileUtil;
import com.chengxin.netdiskdemo.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadFileServiceImpl 的注释
 *
 * @author chengxin
 * @date 2020/3/13 12:19
 * @description
 */
@Component
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    UserFileTableMapper userFileTableMapper;

    @Autowired
    UserDiskCapacityTableMapper userDiskCapacityTableMapper;

    @Autowired
    FileTableMapper fileTableMapper;

    @Value(value = "${server.file.basepath}")
    private String baseServerRootPath;

    @Value(value = "${user.logic.folder.name}")
    private String baseUserRootPath;

    @Override
    public Result checkFile(FilesBase filesBase, String account) {

        //存储user的容量大小
        UserDiskCapacityTable userDiskCapacity;

        //新用户，初始化网盘
        if ((userDiskCapacity = userDiskCapacityTableMapper.selectByAccount(account)) == null) {
            userDiskCapacity = new UserDiskCapacityTable();
            initDiskForNewUser(account, userDiskCapacity);
        }
        //判断user的容量是否足够
        if (userDiskCapacity.getCapacity() < userDiskCapacity.getUsedDisk() + filesBase.getSize()) {
            return new Result(false, StatusEnum.USER_CAPACITY_NOT_ENOUGH, "USER_CAPACITY_NOT_ENOUGH", null);
        }

        //不是默认的folderPath
        if (!filesBase.getFolderName().equals(baseUserRootPath)) {
            //查看文件夹是否存在
            if (userFileTableMapper.selectByFolderName(filesBase.getFolderName()) == null) {
                //不存在发给告诉前端文件夹不存在
                return new Result(false, StatusEnum.FOLDER_NOT_EXISTS, "FOLDER_NOT_EXISTS", null);
            }
        }

        //查看文件名是否重名
        if (userFileTableMapper.selectByFolderFileName(filesBase.getFolderName(), filesBase.getName()) != null) {
            //重名
            return new Result(false, StatusEnum.FILENAME_REPEATED, "FILENAME_REPEATED", null);
        }

        //如果文件库中已经有该文件存在，直接实现秒传
        if (fileTableMapper.selectByCheckCode(filesBase.getCheckCode()) != null) {
            //实现逻辑插入数据
            logicUserFileUpload(filesBase, account);

            //更新user的disk用了的数据
            userDiskCapacity.setUsedDisk(userDiskCapacity.getUsedDisk() + filesBase.getSize());
            userDiskCapacityTableMapper.updateByPrimaryKey(userDiskCapacity);

            return new Result(true, StatusEnum.UPLOAD_FILE_SUCCESS, "", null);
        }

        return new Result(true, StatusEnum.FILE_CHECK_SUCCESS, "", null);
    }

    private void initDiskForNewUser(String account, UserDiskCapacityTable newUserDisk) {
        newUserDisk.setUsedDisk(0.0);
        newUserDisk.setIsVip(UserTypeEnum.NORMAL.value());
        newUserDisk.setCapacity(UserTypeEnum.NORMAL.capacity());
        newUserDisk.setId(IDUtil.getId());
        newUserDisk.setAccount(account);
        userDiskCapacityTableMapper.insert(newUserDisk);
    }

    private void logicUserFileUpload(FilesBase filesBase, String account) {
        UserFileTable userFileTable = new UserFileTable();
        userFileTable.setAccount(account);
        userFileTable.setFatherFolder(filesBase.getFolderName());
        userFileTable.setFileName(filesBase.getName());
        userFileTable.setFileType(filesBase.getFileType().name());
        userFileTable.setFileCheckCode(filesBase.getCheckCode());
        userFileTable.setId(IDUtil.getId());
        userFileTable.setIsDelete(String.valueOf(false));
        userFileTableMapper.insert(userFileTable);
    }

    @Override
    public Result uploadFile(MultipartFile multipartFile, String checkCode, String fileFolder, String account) {

        //配置服务器端的文件路径
        String serverLocalPath = baseServerRootPath + checkCode;

        //负责接收数据，将数据存到服务器本地
        boolean isUpload = FileUtil.upFileHelper(multipartFile, serverLocalPath);

        //服务器本地存储成功
        if (isUpload) {
            //创建文件基础类
            String fileName = FileUtil.getNameFromFiles(multipartFile);
            String type = FileUtil.getTypeFromFiles(multipartFile);
            double size = multipartFile.getSize();
            FilesBase filesBase = new FilesBase(fileName, FileTypeEnum.valueOf(type), size, fileFolder, checkCode);

            //用户逻辑文件存储
            logicUserFileUpload(filesBase, account);
            //更新user已使用的空间大小
            userDiskCapacityTableMapper.updateByAccountAddUsedSize(account, size);

            //服务器本地文件逻辑存储
            serverLogicFileUpdate(checkCode, serverLocalPath, size);

            return new Result(true, StatusEnum.UPLOAD_FILE_SUCCESS, "", null);
        }

        //失败
        return new Result(false, StatusEnum.FILE_UPLOAD_FAILURE, "FILE_UPLOAD_FAILURE", null);

    }

    private void serverLogicFileUpdate(String checkCode, String serverLocalPath, double size) {
        FileTable fileTable = new FileTable();
        fileTable.setLocalPath(serverLocalPath);
        fileTable.setId(IDUtil.getId());
        fileTable.setFileCheckCode(checkCode);
        fileTable.setSize(size);
        fileTableMapper.insert(fileTable);
    }
}
