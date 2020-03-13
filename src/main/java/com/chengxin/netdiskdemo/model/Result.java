package com.chengxin.netdiskdemo.model;

/**
 * Result 的注释
 *
 * @author chengxin
 * @date 2020/3/12 9:10
 * @description
 */
public class Result {

    /**
     * 是否成功
     */
    private boolean isSuccess;

    /**
     * 状态码
     */
    private StatusEnum statusEnum;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 数据
     */
    private Object object;

    public Result(boolean isSuccess, StatusEnum statusEnum, String errorMsg, Object object) {
        this.isSuccess = isSuccess;
        this.statusEnum = statusEnum;
        this.errorMsg = errorMsg;
        this.object = object;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Object getObject() {
        return object;
    }
}
