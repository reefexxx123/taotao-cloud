package com.taotao.cloud.auth.authentication.social.gitee.api;

/**
 * Gitee
 *
 * @author dengtao
 * @date 2020/4/29 20:50
 */
public interface Gitee {

    /**
     * 获取用户信息
     *
     * @return com.taotao.cloud.auth.authentication.social.gitee.api.GiteeUserInfo
     * @author dengtao
     * @date 2020/4/29 20:50
     */
    GiteeUserInfo getUserInfo();
}
