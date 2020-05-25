package com.taotao.cloud.auth.authentication.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 第三方安全认证Properties
 *
 * @author dengtao
 * @date 2020/4/29 20:20
 */
@Data
public class SocialSecurityProperties {
    /**
     * 默认认证页面
     */
    private String filterProcessesUrl = "/social";

    private QQProperties qq = new QQProperties();
    private GithubProperties github = new GithubProperties();
    private GiteeProperties gitee = new GiteeProperties();
    private WeiXinProperties weixin = new WeiXinProperties();
}
