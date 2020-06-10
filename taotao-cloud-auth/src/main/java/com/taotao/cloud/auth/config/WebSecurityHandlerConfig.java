package com.taotao.cloud.auth.config;

import com.taotao.cloud.auth.authentication.properties.SocialOauth2Properties;
import com.taotao.cloud.auth.handler.OauthLogoutHandler;
import com.taotao.cloud.common.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Objects;

/**
 * 认证错误处理器
 *
 * @author dengtao
 * @date 2020/4/29 20:17
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({SocialOauth2Properties.class})
public class WebSecurityHandlerConfig {

    private final TokenStore tokenStore;

    @Bean
    public OauthLogoutHandler oauthLogoutHandler() {
        return new OauthLogoutHandler(tokenStore);
    }

}
