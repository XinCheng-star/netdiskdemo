package com.chengxin.netdiskdemo.model;

/**
 * FileTypeEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:57
 * @description 枚举类，用于表示文件类型
 */
public enum FileTypeEnum {
    FOLDER("FOLDER"),
    PDF("PDF"),
    DOXC("DOXC"),
    DOX("DOX"),
    TXT("TXT"),
    AVI("AVI"),
    MP4("MP4");
    /**
     * 文件类型
     */
    private String name;

    private FileTypeEnum(String name) {
        this.name = name;
    }
}
