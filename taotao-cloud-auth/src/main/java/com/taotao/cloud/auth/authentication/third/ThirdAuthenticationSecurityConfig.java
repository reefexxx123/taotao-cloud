package com.taotao.cloud.auth.authentication.third;

import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.auth.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 第三方登录安全配置
 *
 * @author dengtao
 * @date 2020/4/29 20:28
 */
@Component
public class ThirdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private IUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        ThirdAuthenticationFilter thirdAuthenticationFilter = new ThirdAuthenticationFilter();

        thirdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        thirdAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        thirdAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        ThirdAuthenticationProvider thirdAuthenticationProvider = new ThirdAuthenticationProvider();
        thirdAuthenticationProvider.setUserService(userDetailsService);

        http.authenticationProvider(thirdAuthenticationProvider)
                .addFilterAfter(thirdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
