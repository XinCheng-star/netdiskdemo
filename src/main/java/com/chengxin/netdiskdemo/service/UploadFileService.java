package com.chengxin.netdiskdemo.service;

import com.chengxin.netdiskdemo.model.FilesBase;
import com.chengxin.netdiskdemo.model.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * UploadFileService 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:53
 * @description
 */
@Service
public interface UploadFileService {
    Result checkFile(FilesBase filesBase, String account);

    Result uploadFile(MultipartFile multipartFile, String checkCode, String fileFolder, String account);
}
