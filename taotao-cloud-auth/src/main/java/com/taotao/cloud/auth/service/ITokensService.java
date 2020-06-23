package com.taotao.cloud.auth.service;


import com.taotao.cloud.auth.dto.OAuth2AccessTokenDTO;
import com.taotao.cloud.auth.model.TokenVo;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ITokensService
 *
 * @author dengtao
 * @date 2020/4/29 16:02
 */
public interface ITokensService {

    /**
     * 查询token列表
     *
     * @param params   请求参数
     * @param clientId 应用id
     * @author dengtao
     * @date 2020/4/29 16:02
     */
    PageResult<TokenVo> listTokens(Map<String, Object> params, String clientId);

    /**
     * 获取token
     *
     * @param request  request
     * @param response response
     * @param token    token
     * @author dengtao
     * @date 2020/4/29 17:07
     */
    OAuth2AccessToken getToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token);
}
