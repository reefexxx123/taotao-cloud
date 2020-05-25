package com.taotao.cloud.social.listener;

import com.taotao.cloud.social.entity.GitHubUserInfo;
import com.taotao.cloud.social.entity.GiteeUserInfo;
import com.taotao.cloud.social.entity.QQUserInfo;
import com.taotao.cloud.social.event.GiteeEvent;
import com.taotao.cloud.social.event.GithubEvent;
import com.taotao.cloud.social.event.QQEvent;
import com.taotao.cloud.social.service.SocialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;

/**
 * 功能描述
 *
 * @author dengtao
 * @date 2020/5/14 14:46
 */
@Slf4j
public class SocialListener {

    @Resource
    private SocialService socialService;

    @Async
    @EventListener(GiteeEvent.class)
    public void saveGiteeUserInfo(GiteeEvent event) {
        GiteeUserInfo giteeUserInfo = (GiteeUserInfo) event.getSource();
        socialService.saveGiteeUserInfo(giteeUserInfo);
    }

    @Async
    @EventListener(QQEvent.class)
    public void saveQQUserInfo(QQEvent event) {
        QQUserInfo qqUserInfo = (QQUserInfo) event.getSource();
        socialService.saveQQUserInfo(qqUserInfo);
    }

    @Async
    @EventListener(GithubEvent.class)
    public void saveGithubUserInfo(GithubEvent event) {
        GitHubUserInfo gitHubUserInfo = (GitHubUserInfo) event.getSource();
        socialService.saveGithubUserInfo(gitHubUserInfo);
    }
}
