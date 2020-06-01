package com.taotao.cloud.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cloud.common.utils.ResponseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.annotation.Resource;
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
public class DefaultSecurityHandlerConfig {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 未登录，返回401
     *
     * @return org.springframework.security.web.AuthenticationEntryPoint
     * @author dengtao
     * @date 2020/4/30 09:06
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> ResponseUtil.failed(objectMapper, response, "请求权限不足");

    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    /**
     * 处理spring security oauth 处理失败返回消息格式
     *
     * @return org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
     * @author dengtao
     * @date 2020/4/30 09:06
     */
    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
                ResponseUtil.failed(objectMapper, response, "无效的token拒绝访问");
            }
        };
    }
}
