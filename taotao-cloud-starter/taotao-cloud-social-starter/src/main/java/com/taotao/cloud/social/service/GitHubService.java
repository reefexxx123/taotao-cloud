package com.taotao.cloud.social.service;


import com.taotao.cloud.social.entity.GitHubUserInfo;

/**
 * GitHubService
 *
 * @author dengtao
 * @date 2020/4/29 21:09
*/
public interface GitHubService {
    /**
     * 获取用户信息
     *
     * @param code code
     * @return com.taotao.cloud.social.entity.GitHubUserInfo
     * @author dengtao
     * @date 2020/4/29 21:10
    */
    GitHubUserInfo getUserInfo(String code);

    /**
     * 获取github认证信息
     *
     * @param
     * @return java.lang.String
     * @author dengtao
     * @date 2020/5/13 21:50
    */
    String getGithubAuthUrl();


}
