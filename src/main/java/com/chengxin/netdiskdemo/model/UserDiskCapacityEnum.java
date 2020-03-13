package com.chengxin.netdiskdemo.model;

/**
 * UserDiskCapacityEnum 的注释
 *
 * @author chengxin
 * @date 2020/3/12 18:07
 * @description
 */
public enum UserDiskCapacityEnum {

    VIP(4_000),
    NORMAL(2_000);


    /**
     * value
     */
    private double value;

    UserDiskCapacityEnum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
