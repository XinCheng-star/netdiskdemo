package com.chengxin.netdiskdemo.model;

/**
 * UserTypeEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/12 18:10
 * @description
 */
public enum UserTypeEnum {

    VIP(4_000), NORMAL(2_000);

    /**
     * 最大容量
     */
    private double capacity;

    UserTypeEnum(double capacity) {
        this.capacity = capacity;
    }

    public String value() {
        if (this == VIP) {
            return "true";
        } else {
            return "false";
        }
    }

    public double capacity() {
        return capacity;
    }
}
