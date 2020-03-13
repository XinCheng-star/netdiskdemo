package com.chengxin.netdiskdemo.model;

/**
 * UserStatusEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/12 17:50
 * @description
 */
public enum UserStatusEnum {
    ALIVE("true"),
    DEAD("false");

    /**
     * value
     */
    private String value;

    UserStatusEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
