package com.taotao.cloud.social.entity;

import lombok.Builder;
import lombok.Data;

/**
 * GiteeUserInfo
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@Builder
public class GiteeUserInfo {
    private Integer providerUserId;
    private String avatarUrl;
    private String bio;
    private String blog;
    private String createdAt;
    private String email;
    private String eventsUrl;
    private String followers;
    private String followersUrl;
    private String following;
    private String followingUrl;
    private String gistsUrl;
    private String htmlUrl;
    private String login;
    private String name;
    private String organizationsUrl;
    private String publicGists;
    private String publicRepos;
    private String receivedEventsUrl;
    private String reposUrl;
    private String siteAdmin;
    private String stared;
    private String starredUrl;
    private String subscriptionsUrl;
    private String type;
    private String updatedAt;
    private String url;
    private String watched;
    private String weibo;
}
