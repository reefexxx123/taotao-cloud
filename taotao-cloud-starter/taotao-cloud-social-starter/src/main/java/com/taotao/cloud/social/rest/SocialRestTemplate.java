package com.taotao.cloud.social.rest;

import com.taotao.cloud.common.utils.ContextUtil;
import org.springframework.web.client.RestTemplate;

/**
 * SocialRestTemplate
 *
 * @author dengtao
 * @date 2020/4/29 21:11
 */
public class SocialRestTemplate {
    private static RestTemplate restTemplate = ContextUtil.getBean(RestTemplate.class, true);

    public static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }
}
