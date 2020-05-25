package com.taotao.cloud.auth.authentication.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * SocialAuthenticationFilterPostProcessorImpl
 *
 * @author dengtao
 * @date 2020/4/29 20:36
 */
@Component
public class SocialAuthenticationFilterPostProcessorImpl implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    }
}
