package com.taotao.cloud.social.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * GitHubUserInfo
 *
 * @author dengtao
 * @date 2020/4/29 21:12
 */
@Data
@Builder
public class GitHubUserInfo {
    private long id;
    private String name;
    private String username;
    private String location;
    private String company;
    private String blog;
    private String email;
    private Date createdDate;
    private String profileImageUrl;
    private String avatarUrl;
}
