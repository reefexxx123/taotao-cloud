package com.taotao.cloud.social.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.taotao.cloud.common.enums.LoginTypeEnum;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.common.utils.GsonUtil;
import com.taotao.cloud.social.entity.GiteeUserInfo;
import com.taotao.cloud.social.properties.GiteeProperties;
import com.taotao.cloud.social.service.GiteeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * GiteeServiceImpl
 *
 * @author dengtao
 * @date 2020/4/29 21:09
 */
@Slf4j
@Service
public class GiteeServiceImpl implements GiteeService {

    private static final String URL_GET_USRE_INFO = "https://gitee.com/api/v5/user";

    @Autowired
    private GiteeProperties giteeProperties;
    @Autowired
    private ApplicationEventPublisher publisher;
    private final RestTemplate restTemplate = ContextUtil.getBean(RestTemplate.class, false);

    @Override
    public GiteeUserInfo getUserInfo(String code) {
        try {
            String clientId = giteeProperties.getAppId();
            String clientSecret = giteeProperties.getAppSecret();
            String redirectUri = giteeProperties.getRedirectUri();

            String url = String.format("https://gitee.com/oauth/token?grant_type=authorization_code&code=%s&client_id=%s&redirect_uri=%s&client_secret=%s", code, clientId, redirectUri, clientSecret);
            String post = HttpUtil.post(url, "", 5000);
            Map<String, Object> map = GsonUtil.gson().fromJson(post, Map.class);
            String accessToken = (String) map.get("access_token");
            String scope = (String) map.get("scope");
            String refreshToken = (String) map.get("refresh_token");
            int expiresIn = (Integer) map.get("expires_in");
            log.info("获取Toke的响应:{},scope响应:{},refreshToken响应:{},expiresIn响应:{}", accessToken, scope, refreshToken, expiresIn);

            if (accessToken != null && !"".equals(accessToken)) {
                String userInfoUrl = String.format(URL_GET_USRE_INFO + "?access_token=%s", accessToken);
                Map<String, Object> user = GsonUtil.gson().fromJson(HttpUtil.get(userInfoUrl, 5000), Map.class);
//                JSONObject user = JSON.parseObject(HttpUtil.get(userInfoUrl, 5000));
                if (ObjectUtil.isNotNull(user)) {
                    Integer id = (Integer) user.get("id");
                    String avatarUrl = (String) user.get("avatar_url");
                    String bio = (String) user.get("bio");
                    String blog = (String) user.get("blog");
                    String createdAt = (String) user.get("created_at");
                    String email = (String) user.get("email");
                    String eventsUrl = (String) user.get("events_url");
                    String followers = (String) user.get("followers");
                    String followersUrl = (String) user.get("followers_url");
                    String following = (String) user.get("following");
                    String followingUrl = (String) user.get("following_url");
                    String gistsUrl = (String) user.get("gists_url");
                    String htmlUrl = (String) user.get("html_url");
                    String login = (String) user.get("login");
                    String name = (String) user.get("name");
                    String organizationsUrl = (String) user.get("organizations_url");
                    String publicGists = (String) user.get("public_gists");
                    String publicRepos = (String) user.get("public_repos");
                    String receivedEventsUrl = (String) user.get("received_events_url");
                    String reposUrl = (String) user.get("repos_url");
                    String siteAdmin = (String) user.get("site_admin");
                    String stared = (String) user.get("stared");
                    String starredUrl = (String) user.get("starred_url");
                    String subscriptionsUrl = (String) user.get("subscriptions_url");
                    String type = (String) user.get("type");
                    String updatedAt = (String) user.get("updated_at");
                    String userUrl = (String) user.get("url");
                    String watched = (String) user.get("watched");
                    String weibo = (String) user.get("weibo");

                    GiteeUserInfo userInfo = GiteeUserInfo.builder()
                            .providerUserId(id).avatarUrl(avatarUrl).bio(bio).blog(blog).createdAt(createdAt).email(email)
                            .eventsUrl(eventsUrl).followers(followers).followersUrl(followersUrl).following(following)
                            .followingUrl(followingUrl).gistsUrl(gistsUrl).htmlUrl(htmlUrl).login(login).name(name)
                            .organizationsUrl(organizationsUrl).publicGists(publicGists).publicRepos(publicRepos).receivedEventsUrl(receivedEventsUrl)
                            .reposUrl(reposUrl).siteAdmin(siteAdmin).stared(stared).starredUrl(starredUrl).subscriptionsUrl(subscriptionsUrl)
                            .type(type).updatedAt(updatedAt).url(userUrl).watched(watched).weibo(weibo)
                            .build();
                    log.info("Gitee userInfo : [{}]", userInfo);

//                    publisher.publishEvent(new GiteeEvent(userInfo));
                    return userInfo;
                }
            }
        } catch (Exception e) {
            log.error("码云登录信息错误,{}", e.getLocalizedMessage());
        }
        throw new BaseException("码云登录信息错误", null);
    }

    @Override
    public String getAuthUrl() {
        try {
            return "https://gitee.com/oauth/authorize?response_type=code" +
                    "&client_id=" + giteeProperties.getAppId() +
                    "&redirect_uri=" + URLEncoder.encode(giteeProperties.getRedirectUri(), "UTF-8") +
                    "&state=" + LoginTypeEnum.gitee.getType() +
                    "&scope=user_info";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BaseException("服务异常");
        }

    }
}
