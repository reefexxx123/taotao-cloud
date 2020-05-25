package com.taotao.cloud.auth.authentication.social.gitee.api;

/**
 * @author huan.fu
 * @date 2018/11/26 - 18:12
 */

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import java.util.Map;

/**
 * GiteeImpl
 *
 * @author dengtao
 * @date 2020/4/29 20:50
 */
@Slf4j
public class GiteeImpl extends AbstractOAuth2ApiBinding implements Gitee {

    private static final String URL_GET_USER_INFO = "https://gitee.com/api/v5/user";

    private final String accessToken;

    public GiteeImpl(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public GiteeUserInfo getUserInfo() {
        Map<String, Object> user = JSON.parseObject(HttpUtil.get(String.format(URL_GET_USER_INFO + "?access_token=%s", accessToken), 5000), Map.class);

        if (ObjectUtil.isNotNull(user)) {
            int id = (int) user.get("id");
            String username = String.valueOf(user.get("login"));
            String name = String.valueOf(user.get("name"));
            String avatarUrl = user.get("avatar_url") != null ? String.valueOf(user.get("avatar_url")) : null;

            String url = String.valueOf(user.get("url"));
            String htmlUrl = String.valueOf(user.get("html_url"));
            String followersUrl = String.valueOf(user.get("followers_url"));
            String followingUrl = String.valueOf(user.get("following_url"));
            String blog = user.get("blog") != null ? String.valueOf(user.get("blog")) : null;
            GiteeUserInfo userInfo = GiteeUserInfo.builder()
                    .id(id)
                    .login(username)
                    .name(name)
                    .avatarUrl(avatarUrl)
                    .url(url)
                    .htmlUrl(htmlUrl)
                    .followersUrl(followersUrl)
                    .followingUrl(followingUrl)
                    .blog(blog)
                    .build();
            log.info("Gitee userInfo : [{}]", userInfo);
            return userInfo;
        }
        return null;
    }
}
