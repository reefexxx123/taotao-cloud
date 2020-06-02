package com.taotao.cloud.auth.authentication.social.github.connect;

import com.taotao.cloud.common.utils.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * GithubOAuth2Template
 *
 * @author dengtao
 * @date 2020/4/29 21:00
 */
public class GithubOAuth2Template extends OAuth2Template {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GithubOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        // 自己拼接url
        String clientId = parameters.getFirst("client_id");
        String clientSecret = parameters.getFirst("client_secret");
        String code = parameters.getFirst("code");

        String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s", clientId, clientSecret, code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        String responseStr = getRestTemplate().getForObject(uri, String.class);
        logger.info("获取accessToke的响应：" + responseStr);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        logger.info("获取Toke的响应：" + accessToken);
        return new AccessGrant(accessToken, null, null, null);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = ContextUtil.getBean(RestTemplate.class, true);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
