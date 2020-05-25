package com.taotao.cloud.social.event;

import com.taotao.cloud.social.entity.GitHubUserInfo;
import com.taotao.cloud.social.entity.QQUserInfo;
import org.springframework.context.ApplicationEvent;

/**
 * SysSocialEvent
 *
 * @author dengtao
 * @date 2020/5/14 14:44
 */
public class GithubEvent extends ApplicationEvent {

    public GithubEvent(GitHubUserInfo gitHubUserInfo) {
        super(gitHubUserInfo);
    }
}
