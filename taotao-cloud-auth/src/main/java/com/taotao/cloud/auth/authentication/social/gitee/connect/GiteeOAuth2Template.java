package com.taotao.cloud.auth.authentication.social.gitee.connect;

import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.common.utils.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * GiteeOAuth2Template
 *
 * @author dengtao
 * @date 2020/4/29 20:55
 */
public class GiteeOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public GiteeOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        // 自己拼接url
        String clientId = parameters.getFirst("client_id");
        String clientSecret = parameters.getFirst("client_secret");
        String code = parameters.getFirst("code");
        String redirectUri = parameters.getFirst("redirect_uri");

        String url = String.format("https://gitee.com/oauth/token?grant_type=authorization_code&code=%s&client_id=%s&redirect_uri=%s&client_secret=%s", code, clientId, redirectUri, clientSecret);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        String responseStr = getRestTemplate().getForObject(uri, String.class);

        logger.info("获取accessToke的响应：" + responseStr);
        JSONObject object = JSONObject.parseObject(responseStr);
        String accessToken = (String) object.get("access_token");
        String scope = (String) object.get("scope");
        String refreshToken = (String) object.get("refresh_token");
        int expiresIn = (Integer) object.get("expires_in");

        logger.info("获取Toke的响应:{},scope响应:{},refreshToken响应:{},expiresIn响应:{}", accessToken, scope, refreshToken, expiresIn);
        return new AccessGrant(accessToken, scope, refreshToken, (long) expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = ContextUtil.getBean(RestTemplate.class, true);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}
