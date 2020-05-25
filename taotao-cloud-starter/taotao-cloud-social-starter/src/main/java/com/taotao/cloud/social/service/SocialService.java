package com.taotao.cloud.social.service;


import com.taotao.cloud.social.entity.GitHubUserInfo;
import com.taotao.cloud.social.entity.GiteeUserInfo;
import com.taotao.cloud.social.entity.QQUserInfo;

/**
 * 日志接口
 *
 * @author dengtao
 * @date 2020/5/2 11:18
 */
public interface SocialService {

    /**
     * 功能描述
     *
     * @param userInfo
     * @return void
     * @author dengtao
     * @date 2020/5/14 14:55
    */
    void saveGiteeUserInfo(GiteeUserInfo userInfo);

    /**
     * 功能描述
     *
     * @param userInfo
     * @return void
     * @author dengtao
     * @date 2020/5/14 14:55
    */
    void saveQQUserInfo(QQUserInfo userInfo);

    /**
     * 功能描述
     *
     * @param userInfo
     * @return void
     * @author dengtao
     * @date 2020/5/14 14:55
    */
    void saveGithubUserInfo(GitHubUserInfo userInfo);
}
