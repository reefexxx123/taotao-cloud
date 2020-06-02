package com.taotao.cloud.auth.authentication.third;

import com.taotao.cloud.common.enums.LoginTypeEnum;
import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.auth.token.ThirdAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Objects;

/**
 * 第三方登录提供者
 *
 * @author dengtao
 * @date 2020/4/29 20:27
 */
@Slf4j
public class ThirdAuthenticationProvider implements AuthenticationProvider {

    private IUserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String code = (String) authentication.getPrincipal();
        LoginTypeEnum loginTypeEnum = (LoginTypeEnum) authentication.getCredentials();

        SecurityUser user = userDetailService.loadThirdUser(code, loginTypeEnum);
        if (Objects.isNull(user)) {
            throw new InvalidException("用户在系统中不存在");
        }
        ThirdAuthenticationToken result = new ThirdAuthenticationToken(user, loginTypeEnum, user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ThirdAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserService(IUserDetailsService userService) {
        this.userDetailService = userService;
    }

//    private class DefaultAuthenticationChecks implements UserDetailsChecker {
//        @Override
//        public void check(UserDetails user) {
//            if (!user.isAccountNonLocked()) {
//                log.debug("User account is locked");
//                throw new LockedException(messages.getMessage(
//                        "AbstractUserDetailsAuthenticationProvider.locked",
//                        "User account is locked"));
//            }
//
//            if (!user.isEnabled()) {
//                log.debug("User account is disabled");
//                throw new DisabledException(messages.getMessage(
//                        "AbstractUserDetailsAuthenticationProvider.disabled",
//                        "User is disabled"));
//            }
//
//            if (!user.isAccountNonExpired()) {
//                log.debug("User account is expired");
//                throw new AccountExpiredException(messages.getMessage(
//                        "AbstractUserDetailsAuthenticationProvider.expired",
//                        "User account has expired"));
//            }
//            if (!user.isCredentialsNonExpired()) {
//                log.debug("User account credentials have expired");
//                throw new CredentialsExpiredException(messages.getMessage(
//                        "AbstractUserDetailsAuthenticationProvider.credentialsExpired",
//                        "User credentials have expired"));
//            }
//        }
//    }

}
