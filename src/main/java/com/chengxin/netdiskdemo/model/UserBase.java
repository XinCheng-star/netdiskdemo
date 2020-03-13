package com.chengxin.netdiskdemo.model;

import javax.validation.constraints.NotBlank;

/**
 * UserBase 的注释
 *
 * @author chengxin
 * @date 2020/3/9 22:26
 * @description 基础的用户类
 */
public class UserBase {
    /**
     * 账号 最大32位
     */
    @NotBlank
    private String account;

    /**
     * 密码
     */
    @NotBlank
    private String pwd;

    /**
     * 账号是否有效
     */
    private boolean isActive;

    /**
     * 是否是Vip用户
     */
    private boolean isVip;

    /**
     * 已经用过的大小,单位是MB
     */
    private double size;

    /**
     * 总容量大小，单位是MB
     */
    private double capacity;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
