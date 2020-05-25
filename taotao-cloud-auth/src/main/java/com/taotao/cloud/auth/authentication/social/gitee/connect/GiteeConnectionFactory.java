package com.taotao.cloud.auth.authentication.social.gitee.connect;

import com.taotao.cloud.auth.authentication.social.gitee.api.Gitee;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * GiteeAdapter
 *
 * @author dengtao
 * @date 2020/4/29 20:53
 */
public class GiteeConnectionFactory extends OAuth2ConnectionFactory<Gitee> {

    public GiteeConnectionFactory(String providerId, String clientId, String clientSecret) {
        super(providerId, new GiteeServiceProvider(clientId, clientSecret), new GiteeAdapter());
    }
}
