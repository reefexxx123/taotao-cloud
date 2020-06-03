package com.taotao.cloud.auth.config;

import com.taotao.cloud.auth.authentication.properties.SocialOauth2Properties;
import com.taotao.cloud.auth.handler.OauthLogoutHandler;
import com.taotao.cloud.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import java.util.Objects;

/**
 * 认证错误处理器
 *
 * @author dengtao
 * @date 2020/4/29 20:17
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SocialOauth2Properties.class})
public class WebSecurityHandlerConfig {

    @Bean
    public OauthLogoutHandler oauthLogoutHandler() {
        return new OauthLogoutHandler();
    }

//    @Bean
//    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
//        return new DefaultWebResponseExceptionTranslator() {
//            public static final String BAD_MSG = "坏的凭证";
//
//            @Override
//            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//                OAuth2Exception oAuth2Exception;
//                if (e.getMessage() != null && e.getMessage().equals(BAD_MSG)) {
//                    oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
//                } else if (e instanceof InternalAuthenticationServiceException) {
//                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
//                } else if (e instanceof RedirectMismatchException) {
//                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
//                } else if (e instanceof InvalidScopeException) {
//                    oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
//                } else {
//                    oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
//                }
//                ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
//                ResponseEntity.status(ResultEnum.UNAUTHORIZED.getCode());
//                Objects.requireNonNull(response.getBody()).addAdditionalInformation("code", oAuth2Exception.getHttpErrorCode() + "");
//                response.getBody().addAdditionalInformation("message", oAuth2Exception.getMessage());
//                response.getBody().addAdditionalInformation("data", null);
//
//                return response;
//            }
//        };
//    }

}
