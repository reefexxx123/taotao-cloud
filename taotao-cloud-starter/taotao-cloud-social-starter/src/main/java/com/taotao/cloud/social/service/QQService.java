package com.taotao.cloud.social.service;


import com.taotao.cloud.social.entity.QQUserInfo;

/**
 * QQService
 *
 * @author dengtao
 * @date 2020/4/29 21:10
 */
public interface QQService {

    /**
     * 获取qq用户信息
     *
     * @param code code
     * @return com.taotao.cloud.social.entity.QQUserInfo
     * @author dengtao
     * @date 2020/4/29 21:10
     */
    QQUserInfo getUserInfo(String code);

    /**
     * 获取qq认证url
     *
     * @param
     * @return java.lang.String
     * @author dengtao
     * @date 2020/5/13 21:58
    */
    String getQqAuthUrl();

}
