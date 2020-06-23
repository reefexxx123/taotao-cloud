package com.taotao.cloud.auth.configuration;

import com.taotao.cloud.auth.authentication.mobile.MobileAuthenticationSecurityConfig;
import com.taotao.cloud.auth.authentication.mobile.MobileFilter;
import com.taotao.cloud.auth.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.taotao.cloud.auth.authentication.third.ThirdAuthenticationSecurityConfig;
import com.taotao.cloud.auth.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * oauth2资源服务器
 *
 * @author dengtao
 * @date 2020/4/29 20:16
 */
@Primary
@Slf4j
@Configuration
@Order(1800)
@EnableResourceServer
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;
    private final ThirdAuthenticationSecurityConfig thirdAuthenticationSecurityConfig;
    private final OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final MobileFilter mobileFilter;
    private final TokenStore tokenStore;
    private final OAuth2WebSecurityExpressionHandler expressionHandler;
    private final OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler;
    private final SecurityProperties securityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(securityProperties.getIgnore().getUrls()).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login").permitAll();
        http.logout().permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .httpBasic().disable()
                .headers()
                .frameOptions().disable()
                .and()
                .csrf().disable();

        http.authorizeRequests()
                .and()
                .csrf()
                .disable()
                .apply(openIdAuthenticationSecurityConfig).and()
                .apply(thirdAuthenticationSecurityConfig).and()
                .apply(mobileAuthenticationSecurityConfig).and();

        http.addFilterBefore(mobileFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .stateless(true)
                .authenticationEntryPoint(authenticationEntryPoint)
                .expressionHandler(expressionHandler)
                .accessDeniedHandler(oAuth2AccessDeniedHandler);
    }

}
