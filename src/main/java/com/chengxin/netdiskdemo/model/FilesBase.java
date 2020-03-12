package com.chengxin.netdiskdemo.model;

/**
 * FilesBase 的注释
 *
 * @author chengxin
 * @date 2020/3/9 23:10
 * @description 基础文件类
 */
public class FilesBase {

    /**
     * name 文件名字
     */
    private String name;

    /**
     * fileType 文件类型
     */
    private FileTypeEnum fileType;

    /**
     * 文件大小 默认单位是 MB
     */
    private double size;

    /**
     * 存储文件的文件夹名字
     */
    private String folderName;

    /**
     * 文件的校验码
     */
    private String checkCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileTypeEnum getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
