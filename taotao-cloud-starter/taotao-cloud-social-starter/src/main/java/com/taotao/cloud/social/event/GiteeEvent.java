package com.taotao.cloud.social.event;

import com.taotao.cloud.social.entity.GiteeUserInfo;
import org.springframework.context.ApplicationEvent;

/**
 * SysSocialEvent
 *
 * @author dengtao
 * @date 2020/5/14 14:44
 */
public class GiteeEvent extends ApplicationEvent {

    public GiteeEvent(GiteeUserInfo giteeUserInfo) {
        super(giteeUserInfo);
    }
}
