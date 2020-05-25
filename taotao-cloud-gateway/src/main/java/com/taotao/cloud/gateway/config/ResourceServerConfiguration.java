package com.taotao.cloud.gateway.config;

import com.taotao.cloud.auth.properties.PermitProperties;
import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.gateway.auth.*;
import com.taotao.cloud.gateway.handler.JsonAccessDeniedHandler;
import com.taotao.cloud.gateway.handler.Oauth2AuthSuccessHandler;
import com.taotao.cloud.gateway.properties.CustomGatewayProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;

import java.util.Arrays;

/**
 * 资源服务器配置
 *
 * @author dengtao
 * @date 2020/4/29 22:11
 */
@Configuration
@AllArgsConstructor
public class ResourceServerConfiguration {

    private final SecurityProperties securityProperties;
    private final TokenStore tokenStore;
    private final PermissionAuthManager permissionAuthManager;
    private final CustomGatewayProperties customGatewayProperties;

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //认证处理器
        ReactiveAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(tokenStore);

        //token转换器
        ServerBearerTokenAuthenticationConverter tokenAuthenticationConverter = new ServerBearerTokenAuthenticationConverter();
        tokenAuthenticationConverter.setAllowUriQueryParameter(true);

        JsonAuthenticationEntryPoint entryPoint = new JsonAuthenticationEntryPoint();
        //oauth2认证过滤器
        AuthenticationWebFilter oauth2Filter = new AuthenticationWebFilter(customAuthenticationManager);
        oauth2Filter.setServerAuthenticationConverter(tokenAuthenticationConverter);
        oauth2Filter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
        oauth2Filter.setAuthenticationSuccessHandler(new Oauth2AuthSuccessHandler());

        http.addFilterAt(oauth2Filter, SecurityWebFiltersOrder.AUTHENTICATION);

        ServerHttpSecurity.AuthorizeExchangeSpec authorizeExchange = http.authorizeExchange();
        PermitProperties ignore = securityProperties.getIgnore();
        ignore.setHttpUrls(Arrays.stream(ignore.getHttpUrls()).map(uri -> customGatewayProperties.getBaseUri() + uri).toArray(String[]::new));
        if (ignore.getUrls().length > 0) {
            authorizeExchange.pathMatchers(securityProperties.getIgnore().getUrls()).permitAll();
        }

        authorizeExchange
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange()
                .access(permissionAuthManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new JsonAccessDeniedHandler())
                .authenticationEntryPoint(entryPoint)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .httpBasic().disable()
                .csrf().disable();
        return http.build();
    }
}
