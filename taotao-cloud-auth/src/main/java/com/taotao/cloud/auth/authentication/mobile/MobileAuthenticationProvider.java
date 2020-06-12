package com.taotao.cloud.auth.authentication.mobile;

import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.auth.token.MobileAuthenticationToken;
import com.taotao.cloud.common.model.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

import java.util.Objects;

/**
 * 手机认证过滤器提供者
 *
 * @author dengtao
 * @date 2020/4/29 20:26
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private IUserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        SecurityUser securityUser = userDetailService.loadUserByMobile(mobile);
        if (Objects.isNull(securityUser)) {
            throw new InvalidException("用户在系统中不存在");
        }
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(securityUser, securityUser.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    /**
     * 只有Authentication为SmsCodeAuthenticationToken使用此Provider认证
     *
     * @param aClass class
     * @return boolean
     * @author dengtao
     * @date 2020/5/14 21:30
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return MobileAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public IUserDetailsService getUserDetailService() {
        return userDetailService;
    }

    public void setUserDetailService(IUserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

}
