package com.taotao.cloud.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cloud.auth.dto.OAuth2AccessTokenDTO;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.auth.util.AuthUtils;
import com.taotao.cloud.common.utils.ResponseUtil;
import com.taotao.cloud.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 *
 * @author dengtao
 * @date 2020/5/13 16:15
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final String BASIC_PREFIX = "Basic ";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Resource
    private ObjectMapper objectMapper;

    @Qualifier("defaultAuthorizationServerTokenServices")
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String header = request.getHeader("BasicAuthorization");
        if (header == null || !header.startsWith(BASIC_PREFIX)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        final String[] clientInfos = AuthUtils.extractClient(request);
        String clientId = clientInfos[0];
        String clientSecret = clientInfos[1];

        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser) {
            SecurityUser user = (SecurityUser) principal;
            //存储认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成token
            ClientDetails clientDetails = this.getClient(clientId, clientSecret);
            TokenRequest tokenRequest = new TokenRequest(WebUtils.getAllRequestParam(request), clientId, clientDetails.getScope(), "password");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);
            OAuth2AccessTokenDTO tokenDTO = new OAuth2AccessTokenDTO();
            tokenDTO.setToken(oAuth2AccessToken);
            user.setPassword("");
            tokenDTO.setUser(user);
            ResponseUtil.success(objectMapper, response, tokenDTO);
        }
    }

    private ClientDetails getClient(String clientId, String clientSecret) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }
}

