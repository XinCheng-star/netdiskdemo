package com.chengxin.netdiskdemo.model;

/**
 * StatusEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/12 9:13
 * @description
 */
public enum StatusEnum {

    SUCCESS("SUCCESS"),
    NOT_FOUND("NOT_FOUND"),
    SERVER_ERROR("SERVER_ERROR"),
    NOT_LOG_ON("USER_NOT_LOG_ON"),
    USER_NOT_VALID("USER_NOT_VALID"),
    WRONG_PWD("WRONG_PWD"),
    DOUBLE_LOG_ON("DOUBLE_LOG_ON"),
    PARAM_ERROR("PARAM_ERROR"),
    INSERT_ERROR("INSERT_ERROR"),
    DOUBLE_LOG_IN("DOUBLE_LOG_IN"),
    SESSION_PARAM_LOST("SESSION_PARAM_LOST");

    private String info;

    StatusEnum(String info) {
        this.info = info;
    }

    public int toValue() {
        return Integer.parseInt(info);
    }
}
