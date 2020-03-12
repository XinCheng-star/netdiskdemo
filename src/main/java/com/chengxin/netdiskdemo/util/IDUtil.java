package com.chengxin.netdiskdemo.util;

import java.util.UUID;

/**
 * IDUtil 的注释
 *
 * @author chengxin
 * @date 2020/3/9 23:21
 * @description
 */
public class IDUtil {
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "").trim();
    }
}
