package com.taotao.cloud.auth.authentication.social;

import lombok.Data;

/**
 * SocialUserInfo
 *
 * @author dengtao
 * @date 2020/4/29 20:34
 */
@Data
public class SocialUserInfo {

    private String providerId;
    private String providerUserId;
    private String nickname;
    private String headImg;
}
