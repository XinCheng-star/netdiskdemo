package com.chengxin.netdiskdemo.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5Util 的注释
 *
 * @author chengxin
 * @date 2020/3/9 23:24
 * @description
 */
public class MD5Util {
    public static String getMd5FromFile(byte[] filesBytes) {
        return DigestUtils.md5Hex(filesBytes);
    }

    public static String getMd5FromPwd(String pwd) {
        return DigestUtils.md5Hex(pwd);
    }
}
