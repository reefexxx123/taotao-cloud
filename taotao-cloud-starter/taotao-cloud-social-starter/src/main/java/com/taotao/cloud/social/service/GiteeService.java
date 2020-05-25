package com.taotao.cloud.social.service;


import com.taotao.cloud.social.entity.GiteeUserInfo;

import java.io.UnsupportedEncodingException;

/**
 * GiteeService
 *
 * @author dengtao
 * @date 2020/4/29 21:09
 */
public interface GiteeService {
    /**
     * 获取用户信息
     *
     * @param code code
     * @return com.taotao.cloud.social.entity.GiteeUserInfo
     * @author dengtao
     * @date 2020/4/29 21:09
     */
    GiteeUserInfo getUserInfo(String code);

    /**
     * 获取认证路径
     *
     * @param
     * @return java.lang.String
     * @author dengtao
     * @date 2020/5/13 21:34
    */
    String getAuthUrl();
}
