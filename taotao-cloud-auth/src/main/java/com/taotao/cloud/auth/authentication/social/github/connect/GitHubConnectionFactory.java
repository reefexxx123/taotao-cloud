package com.taotao.cloud.auth.authentication.social.github.connect;

import com.taotao.cloud.auth.authentication.social.github.api.GitHub;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * GitHubConnectionFactory
 *
 * @author dengtao
 * @date 2020/4/29 21:00
 */
public class GitHubConnectionFactory extends OAuth2ConnectionFactory<GitHub> {

    public GitHubConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new GitHubServiceProvider(clientId, clientSecret), new GitHubAdapter());
    }
}
