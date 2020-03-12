package com.chengxin.netdiskdemo.model;

/**
 * UserTypeEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/12 18:10
 * @description
 */
public enum UserTypeEnum {
    VIP, NORMAL;


    public String value() {
        if (this == VIP) {
            return "true";
        } else {
            return "false";
        }
    }
}
