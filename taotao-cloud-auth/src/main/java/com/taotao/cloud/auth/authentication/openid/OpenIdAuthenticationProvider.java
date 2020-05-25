package com.taotao.cloud.auth.authentication.openid;

import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.auth.token.OpenIdAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * OpenId登录提供者
 *
 * @author dengtao
 * @date 2020/4/29 21:24
 */
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private IUserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;
        String openId = (String) authenticationToken.getPrincipal();
        UserDetails user = userDetailService.loadUserByOpenId(openId);
        if (user == null) {
            throw new InvalidException("openId认证失败");
        }
        OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public IUserDetailsService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(IUserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }
}
