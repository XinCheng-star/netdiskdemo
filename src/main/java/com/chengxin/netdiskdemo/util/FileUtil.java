package com.chengxin.netdiskdemo.util;

import com.chengxin.netdiskdemo.dataobject.UserFileTable;
import com.chengxin.netdiskdemo.model.Result;
import com.chengxin.netdiskdemo.model.StatusEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileUtil 的注释
 *
 * @author chengxin
 * @date 2020/3/13 16:17
 * @description
 */
public class FileUtil {

    private static int TRANS_UNIT = 2048;

    public static boolean upFileHelper(MultipartFile multipartFile, String serverPath) {

        //创建新的文件夹
        File file = new File(serverPath);
        if (!file.exists()) {
            try {
                //父文件夹是否存在
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        //接受文件流的数据
        try {
            byte[] bytes = new byte[TRANS_UNIT];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            while (bufferedInputStream.read(bytes) > 0) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getNameFromFiles(MultipartFile multipartFile) {
        String path = multipartFile.getOriginalFilename();
        return path.substring(0, path.lastIndexOf("."));
    }

    public static String getTypeFromFiles(MultipartFile multipartFile) {
        String path = multipartFile.getOriginalFilename();
        return path.substring(path.lastIndexOf(".") + 1).toUpperCase();
    }

    public static String getFileNameFromPath(String path) {

        path = path.replace(" ","");
        //path 格式是 /foldername/filename or only is /filename
        String fileName = path.substring(path.lastIndexOf("/")+1);
        if (fileName.contains(".")) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }

    public static String getFileFolderFromPath(String path) {
        path = path.replace(" ","");
        //path 格式是 /foldername/filename or only is /filename
        String fileName = path.substring(path.lastIndexOf("/")+1);
        path = path.replace("/"+fileName,"");

        if(path.length() == 0){
            return "./";
        }else{
            return path.substring(path.indexOf("/")+1);
        }

    }

    public static Result downloadFileHelper(UserFileTable table,
                                            String serverFilePath,
                                            HttpServletResponse response,
                                            HttpServletRequest request) {

        String filenames = table.getFileName() + "." + table.getFileType().toLowerCase();

        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");

        try {
            File file = new File(serverFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + filenames);

            //将文件数据写入servletOutput里面去
            byte[] bytes = new byte[TRANS_UNIT];
            ServletOutputStream outputStream = response.getOutputStream();

            while (fileInputStream.read(bytes) > 0) {
                outputStream.write(bytes);
            }
            response.flushBuffer();
            return new Result(true, StatusEnum.SUCCESS,"",null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Result(false, StatusEnum.SERVER_FILE_NOT_FIND,"SERVER_FILE_NOT_FIND",null);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, StatusEnum.SERVER_ERROR,"SERVER_ERROR IO ERROR",null);
        }
    }

}
