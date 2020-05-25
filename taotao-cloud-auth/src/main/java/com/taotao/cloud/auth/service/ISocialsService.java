package com.taotao.cloud.auth.service;


/**
 * ISocialsService
 *
 * @author dengtao
 * @date 2020/4/29 16:02
 */
public interface ISocialsService {

    /**
     * 获取认证url
     *
     * @param loginType
     * @return java.lang.String
     * @author dengtao
     * @date 2020/5/13 22:10
    */
    String getAuthUrl(String loginType);
}
