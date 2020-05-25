package com.taotao.cloud.auth.authentication.social.github.connect;

import com.taotao.cloud.auth.authentication.social.github.api.GitHub;
import com.taotao.cloud.auth.authentication.social.github.api.GitHubImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * github 社交登录的自动配置
 *
 * @author dengtao
 * @date 2020/4/29 21:00
 */
public class GitHubServiceProvider extends AbstractOAuth2ServiceProvider<GitHub> {

    public GitHubServiceProvider(String clientId, String clientSecret) {
        super(new GithubOAuth2Template(clientId, clientSecret, "https://github.com/login/oauth/authorize", "https://github.com/login/oauth/access_token"));
    }

    @Override
    public GitHub getApi(String accessToken) {
        return new GitHubImpl(accessToken);
    }
}
