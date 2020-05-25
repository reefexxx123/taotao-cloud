package com.taotao.cloud.auth.authentication.social;

import lombok.Data;

import java.io.Serializable;

/**
 * 第三方数据类
 *
 * @author dengtao
 * @date 2020/4/29 20:28
 */
@Data
public class TaotaoConnectionData implements Serializable {
    private String providerId;
    private String providerUserId;
    private String displayName;
    private String profileUrl;
    private String imageUrl;
    private String accessToken;
    private String secret;
    private String refreshToken;
    private Long expireTime;
}
