package com.taotao.cloud.social.service.impl;

import com.taotao.cloud.common.enums.LoginTypeEnum;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.social.properties.GitHubProperties;
import com.taotao.cloud.social.rest.SocialRestTemplate;
import com.taotao.cloud.social.entity.GitHubUserInfo;
import com.taotao.cloud.social.event.GithubEvent;
import com.taotao.cloud.social.service.GitHubService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * GitHubServiceImpl
 *
 * @author dengtao
 * @date 2020/4/29 21:10
 */
@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {

    private static final String USER_INFO_URL = "https://api.github.com/user";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.ENGLISH);

    @Autowired
    private GitHubProperties gitHubProperties;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public GitHubUserInfo getUserInfo(String code) {
        try {
            // 自己拼接url
            String clientId = gitHubProperties.getAppId();
            String clientSecret = gitHubProperties.getAppSecret();

            String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s", clientId, clientSecret, code);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            URI uri = builder.build().encode().toUri();
            String responseStr = SocialRestTemplate.getRestTemplate().getForObject(uri, String.class);
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
            String accessToken = StringUtils.substringAfterLast(items[0], "=");
            log.info("获取Toke的响应：" + accessToken);

            Map<String, ?> user = SocialRestTemplate.getRestTemplate().getForObject(USER_INFO_URL, Map.class);
            Long gitHubId = Long.valueOf(String.valueOf(user.get("id")));
            String username = String.valueOf(user.get("login"));
            String name = String.valueOf(user.get("name"));
            String location = user.get("location") != null ? String.valueOf(user.get("location")) : null;
            String company = user.get("company") != null ? String.valueOf(user.get("company")) : null;
            String blog = user.get("blog") != null ? String.valueOf(user.get("blog")) : null;
            String email = user.get("email") != null ? String.valueOf(user.get("email")) : null;
            Date createdDate = toDate(String.valueOf(user.get("created_at")), dateFormat);
            String gravatarId = (String) user.get("gravatar_id");
            String profileImageUrl = gravatarId != null ? "https://secure.gravatar.com/avatar/" + gravatarId : null;
            String avatarUrl = user.get("avatar_url") != null ? String.valueOf(user.get("avatar_url")) : null;

            GitHubUserInfo userInfo = GitHubUserInfo.builder()
                    .id(gitHubId)
                    .username(username)
                    .name(name)
                    .location(location)
                    .company(company)
                    .blog(blog)
                    .email(email)
                    .profileImageUrl(profileImageUrl)
                    .avatarUrl(avatarUrl)
                    .createdDate(createdDate)
                    .build();
            log.info("github userInfo : [{}]", userInfo);

            publisher.publishEvent(new GithubEvent(userInfo));
            return userInfo;
        } catch (Exception e) {
            log.error("GitHub登录信息错误,{}", e.getLocalizedMessage());
        }
        throw new BaseException("GitHub登录信息错误", null);
    }

    @Override
    public String getGithubAuthUrl() {
        try {
            return "https://github.com/login/oauth/authorize" +
                    "&client_id=" + gitHubProperties.getAppId() +
                    "&redirect_uri=" + URLEncoder.encode(gitHubProperties.getRedirectUri(), "UTF-8") +
                    "&state=" + LoginTypeEnum.github.getType() +
                    "&scope=user";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BaseException("服务异常");
        }
    }

    private Date toDate(String dateString, DateFormat dateFormat) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
