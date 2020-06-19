package com.taotao.cloud.auth.config;

import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.auth.service.impl.UserDetailsServiceImpl;
import com.taotao.cloud.common.utils.ResponseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DefaultSecurityHandlerConfig
 *
 * @author dengtao
 * @date 2020/4/30 09:05
 */
public class SecurityHandlerConfig {

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> ResponseUtil.failed(response, "请求权限不足");
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
                ResponseUtil.failed(response, "无效的token拒绝访问");
            }
        };
    }

    @Bean
    public IUserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
}
