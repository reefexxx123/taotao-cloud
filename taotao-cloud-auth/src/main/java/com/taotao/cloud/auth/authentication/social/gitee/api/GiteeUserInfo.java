package com.taotao.cloud.auth.authentication.social.gitee.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Gitee 用户信息
 *
 * @author dengtao
 * @date 2020/4/29 20:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiteeUserInfo {
    private int id;
    private String login;
    private String name;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String blog;
}
