package com.taotao.cloud.social.properties;

/**
 * SocialProperties
 *
 * @author dengtao
 * @date 2020/4/29 21:11
 */
public abstract class BaseSocialProperties {
    private String appId;
    private String appSecret;

    public BaseSocialProperties() {
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
