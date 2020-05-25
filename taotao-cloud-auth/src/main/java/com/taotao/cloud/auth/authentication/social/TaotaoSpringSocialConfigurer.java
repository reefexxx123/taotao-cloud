package com.taotao.cloud.auth.authentication.social;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * TaotaoSpringSocialConfigurer
 *
 * @author dengtao
 * @date 2020/4/29 20:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaotaoSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public TaotaoSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);

        filter.setFilterProcessesUrl(filterProcessesUrl);

        if (socialAuthenticationFilterPostProcessor != null) {
            socialAuthenticationFilterPostProcessor.process(filter);
        }
        filter.setSignupUrl("/socialSignUp");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return (T) filter;
    }

}
