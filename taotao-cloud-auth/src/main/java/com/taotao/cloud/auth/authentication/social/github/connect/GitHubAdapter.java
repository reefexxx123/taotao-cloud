package com.taotao.cloud.auth.authentication.social.github.connect;

import com.taotao.cloud.auth.authentication.social.github.api.GitHub;
import com.taotao.cloud.auth.authentication.social.github.api.GitHubUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * GitHubAdapter
 *
 * @author dengtao
 * @date 2020/4/29 20:59
 */
public class GitHubAdapter implements ApiAdapter<GitHub> {

    @Override
    public boolean test(GitHub api) {
        return true;
    }

    @Override
    public void setConnectionValues(GitHub api, ConnectionValues values) {
        GitHubUserInfo userInfo = api.getUserInfo();
        values.setProviderUserId(String.valueOf(userInfo.getId()));
        values.setDisplayName(userInfo.getUsername());
        values.setProfileUrl("https://github.com/" + userInfo.getId());
        values.setImageUrl(userInfo.getAvatarUrl());
    }

    @Override
    public UserProfile fetchUserProfile(GitHub api) {
        return null;
    }

    @Override
    public void updateStatus(GitHub api, String message) {

    }
}
