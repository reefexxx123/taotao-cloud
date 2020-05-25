package com.taotao.cloud.auth.authentication.social.gitee.connect;

import com.taotao.cloud.auth.authentication.social.gitee.api.Gitee;
import com.taotao.cloud.auth.authentication.social.gitee.api.GiteeImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Gitee 社交登录的自动配置
 *
 * @author dengtao
 * @date 2020/4/29 20:54
 */
public class GiteeServiceProvider extends AbstractOAuth2ServiceProvider<Gitee> {

    public GiteeServiceProvider(String clientId, String clientSecret) {
        super(new GiteeOAuth2Template(clientId, clientSecret, "https://gitee.com/oauth/authorize", "https://gitee.com/oauth/token"));
    }

    @Override
    public Gitee getApi(String accessToken) {
        return new GiteeImpl(accessToken);
    }
}
