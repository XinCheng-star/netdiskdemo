package com.chengxin.netdiskdemo.service;

import com.chengxin.netdiskdemo.model.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DonloadfileService 的注释
 *
 * @author chengxin
 * @date 2020/3/9 21:54
 * @description
 */
@Service
public interface DonloadfileService {
    Result downloadFile(String filePath, String account, HttpServletResponse response, HttpServletRequest request);
}
