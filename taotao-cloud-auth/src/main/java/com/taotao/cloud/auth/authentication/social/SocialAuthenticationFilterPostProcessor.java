package com.taotao.cloud.auth.authentication.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * SocialAuthenticationFilterPostProcessor
 *
 * @author dengtao
 * @date 2020/4/29 20:35
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * 处理过滤器
     *
     * @param socialAuthenticationFilter socialAuthenticationFilter
     * @return void
     * @author dengtao
     * @date 2020/4/29 20:35
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

