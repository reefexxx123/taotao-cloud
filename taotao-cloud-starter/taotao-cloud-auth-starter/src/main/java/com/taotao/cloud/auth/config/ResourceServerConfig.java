package com.taotao.cloud.auth.config;

import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * oauth2资源服务器
 *
 * @author dengtao
 * @date 2020/4/30 09:04
 */
@Slf4j
@Order(100)
@EnableResourceServer
@ConditionalOnProperty(prefix = "taotao.cloud.oauth2.security", name = "enabled", havingValue = "true")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter implements InitializingBean {

    @Resource
    private TokenStore tokenStore;
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Resource
    private OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
<<<<<<< HEAD
=======
    private RemoteTokenServices remoteTokenServices;
    @Resource
>>>>>>> dev
    private RestTemplate restTemplate;

    @Override
    public void afterPropertiesSet() {
        log.info("[TAOTAO CLOUD][" + StarterNameConstant.TAOTAO_CLOUD_AUTH_STARTER + "]" + "资源服务器已启动");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = setHttp(http)
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers(securityProperties.getIgnore().getUrls()).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest();
        setAuthenticate(authorizedUrl);

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
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        remoteTokenServices.setRestTemplate(restTemplate);
        resources.tokenStore(tokenStore)
<<<<<<< HEAD
=======
                .tokenServices(remoteTokenServices)
                .stateless(true)
>>>>>>> dev
                .authenticationEntryPoint(authenticationEntryPoint)
                .expressionHandler(expressionHandler)
                .accessDeniedHandler(oAuth2AccessDeniedHandler);

        RemoteTokenServices remoteTokenServices = ContextUtil.getBean(RemoteTokenServices.class, true);
        if (Objects.nonNull(remoteTokenServices)) {
            remoteTokenServices.setRestTemplate(restTemplate);
            resources.tokenServices(remoteTokenServices);
        }
    }

    /**
     * 留给子类重写扩展功能
     *
     * @param http http
     */
    public HttpSecurity setHttp(HttpSecurity http) {
        return http;
    }

    /**
     * url权限控制，默认是认证就通过，可以重写实现个性化
     *
     * @param authorizedUrl authorizedUrl
     */
    public HttpSecurity setAuthenticate(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl) {
        return authorizedUrl.authenticated().and();
    }
}
