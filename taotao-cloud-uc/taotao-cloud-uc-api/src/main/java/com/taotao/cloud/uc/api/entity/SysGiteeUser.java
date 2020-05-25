package com.taotao.cloud.uc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Gitee 用户信息
 *
 * @author dengtao
 * @date 2020/4/29 20:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_gitee_user")
public class SysGiteeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

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
