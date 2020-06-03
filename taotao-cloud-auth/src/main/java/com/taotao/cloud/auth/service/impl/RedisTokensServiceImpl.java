package com.taotao.cloud.auth.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.auth.dto.OAuth2AccessTokenDTO;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.auth.model.TokenVo;
import com.taotao.cloud.auth.service.ITokensService;
import com.taotao.cloud.auth.utils.AuthUtil;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.WebUtil;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * token管理服务(redis token)
 *
 * @author dengtao
 * @date 2020/4/29 16:03
 */
@Slf4j
@Service
public class RedisTokensServiceImpl implements ITokensService {

    @Autowired
    private RedisRepository redisRepository;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public Result<PageResult<TokenVo>> listTokens(Map<String, Object> params, String clientId) {
        Integer page = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        int[] startEnds = PageUtil.transToStartEnd(page, limit);
        //根据请求参数生成redis的key
        String redisKey = getRedisKey(params, clientId);
        long size = redisRepository.length(redisKey);
        List<TokenVo> result = new ArrayList<>(limit);
        //查询token集合
        List<Object> tokenObjs = redisRepository.getList(redisKey, startEnds[0], startEnds[1] - 1);
        if (tokenObjs != null) {
            for (Object obj : tokenObjs) {
                DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) obj;
                //构造token对象
                TokenVo tokenVo = new TokenVo();
                tokenVo.setTokenValue(accessToken.getValue());
                tokenVo.setExpiration(accessToken.getExpiration());

                //获取用户信息
                Object authObj = redisRepository.get(SecurityConstant.REDIS_TOKEN_AUTH + accessToken.getValue());
                OAuth2Authentication authentication = (OAuth2Authentication) authObj;
                if (authentication != null) {
                    OAuth2Request request = authentication.getOAuth2Request();
                    tokenVo.setUsername(authentication.getName());
                    tokenVo.setClientId(request.getClientId());
                    tokenVo.setGrantType(request.getGrantType());
                }

                result.add(tokenVo);
            }
        }
        PageResult<TokenVo> pageResult = PageResult.<TokenVo>builder().data(result).code(ResultEnum.SUCCESS.getCode())
                .total(size).currentPage(page).build();
        return Result.succeed(pageResult);
    }

    @Override
    public OAuth2AccessTokenDTO getToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token) {
        try {
            final String[] clientInfos = AuthUtil.extractClient(request);
            String clientId = clientInfos[0];
            String clientSecret = clientInfos[1];

            ClientDetails clientDetails = getClient(clientId, clientSecret);

            TokenRequest tokenRequest = new TokenRequest(WebUtil.getAllRequestParam(request), clientId,
                    clientDetails.getScope(), "password");

            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);

            OAuth2AccessTokenDTO tokenDTO = new OAuth2AccessTokenDTO();
            tokenDTO.setToken(oAuth2AccessToken);
            Object principal = oAuth2Authentication.getUserAuthentication().getPrincipal();
            if (principal instanceof SecurityUser) {
                SecurityUser user = (SecurityUser) principal;
                user.setPassword("");
                tokenDTO.setUser(user);
            }
            return tokenDTO;
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            log.error("获取token失败， {}", e.getMessage());
            return null;
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

    /**
     * 根据请求参数生成redis的key
     *
     * @param params   params
     * @param clientId clientId
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 16:03
     */
    private String getRedisKey(Map<String, Object> params, String clientId) {
        String result;
        String username = MapUtils.getString(params, "username");
        if (StrUtil.isNotEmpty(username)) {
            result = SecurityConstant.REDIS_UNAME_TO_ACCESS + clientId + ":" + username;
        } else {
            result = SecurityConstant.REDIS_CLIENT_ID_TO_ACCESS + clientId;
        }
        return result;
    }
}
