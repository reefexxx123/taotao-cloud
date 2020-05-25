package com.taotao.cloud.auth.authentication.social.github.api;

/**
 * GitHub
 *
 * @author dengtao
 * @date 2020/4/29 20:57
 */
public interface GitHub {

    /**
     * 获取用户信息
     *
     * @return com.taotao.cloud.auth.authentication.social.github.api.GitHubUserInfo
     * @author dengtao
     * @date 2020/4/29 20:57
    */
    GitHubUserInfo getUserInfo();
}
