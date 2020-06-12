package com.taotao.cloud.social.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.common.enums.LoginTypeEnum;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.utils.ContextUtil;
import com.taotao.cloud.social.properties.GiteeProperties;
import com.taotao.cloud.social.entity.GiteeUserInfo;
import com.taotao.cloud.social.service.GiteeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
            JSONObject object = JSONObject.parseObject(post);
            String accessToken = (String) object.get("access_token");
            String scope = (String) object.get("scope");
            String refreshToken = (String) object.get("refresh_token");
            int expiresIn = (Integer) object.get("expires_in");
            log.info("获取Toke的响应:{},scope响应:{},refreshToken响应:{},expiresIn响应:{}", accessToken, scope, refreshToken, expiresIn);

            if (accessToken != null && !"".equals(accessToken)) {
                String userInfoUrl = String.format(URL_GET_USRE_INFO + "?access_token=%s", accessToken);
                JSONObject user = JSON.parseObject(HttpUtil.get(userInfoUrl, 5000));
                if (ObjectUtil.isNotNull(user)) {
                    Integer id = user.getInteger("id");
                    String avatarUrl = user.getString("avatar_url");
                    String bio = user.getString("bio");
                    String blog = user.getString("blog");
                    String createdAt = user.getString("created_at");
                    String email = user.getString("email");
                    String eventsUrl = user.getString("events_url");
                    String followers = user.getString("followers");
                    String followersUrl = user.getString("followers_url");
                    String following = user.getString("following");
                    String followingUrl = user.getString("following_url");
                    String gistsUrl = user.getString("gists_url");
                    String htmlUrl = user.getString("html_url");
                    String login = user.getString("login");
                    String name = user.getString("name");
                    String organizationsUrl = user.getString("organizations_url");
                    String publicGists = user.getString("public_gists");
                    String publicRepos = user.getString("public_repos");
                    String receivedEventsUrl = user.getString("received_events_url");
                    String reposUrl = user.getString("repos_url");
                    String siteAdmin = user.getString("site_admin");
                    String stared = user.getString("stared");
                    String starredUrl = user.getString("starred_url");
                    String subscriptionsUrl = user.getString("subscriptions_url");
                    String type = user.getString("type");
                    String updatedAt = user.getString("updated_at");
                    String userUrl = user.getString("url");
                    String watched = user.getString("watched");
                    String weibo = user.getString("weibo");

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
