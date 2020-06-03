package com.taotao.cloud.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.auth.service.PermissionService;
import com.taotao.cloud.auth.utils.AuthUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.context.TenantContextHolder;
import com.taotao.cloud.auth.model.SecurityMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 请求权限判断service
 *
 * @author dengtao
 * @date 2020/5/1 08:02
 */
@Slf4j
public abstract class AbstractPermissionService implements PermissionService {

    @Autowired
    private SecurityProperties securityProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(Authentication authentication, String requestMethod, String requestURI) {
        // 前端跨域OPTIONS请求预检放行 也可通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(requestMethod)) {
            return true;
        }

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //判断是否开启url权限验证
            Boolean enable = securityProperties.getAuth().getUrlPermission().getEnable();
            if (!enable) {
                return true;
            }

            //超级管理员admin不需认证
            String username = AuthUtil.getUsername(authentication);
            //todo  这里要修改
            if (!CommonConstant.ADMIN_USER_NAME.equals(username)) {
                return true;
            }

            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            //判断应用黑白名单
            if (!isNeedAuth(auth2Authentication.getOAuth2Request().getClientId())) {
                return true;
            }

            //判断不进行url权限认证的api，所有已登录用户都能访问的url
            for (String path : securityProperties.getAuth().getUrlPermission().getIgnoreUrls()) {
                if (antPathMatcher.match(path, requestURI)) {
                    return true;
                }
            }

            List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
            if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return false;
            }

            //保存租户信息
            String clientId = auth2Authentication.getOAuth2Request().getClientId();
            TenantContextHolder.setTenant(clientId);

            String roleCodes = grantedAuthorityList.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.joining(", "));
            List<SecurityMenu> menuList = findMenuByRoleCodes(roleCodes);
            for (SecurityMenu menu : menuList) {
                if (StrUtil.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), requestURI)) {
                    if (StrUtil.isNotEmpty(menu.getPathMethod())) {
                        return requestMethod.equalsIgnoreCase(menu.getPathMethod());
                    } else {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 判断应用是否满足白名单和黑名单的过滤逻辑
     *
     * @param clientId 应用id
     * @return true(需要认证)，false(不需要认证)
     */
    private boolean isNeedAuth(String clientId) {
        boolean result = true;
        //白名单
        List<String> includeClientIds = securityProperties.getAuth().getUrlPermission().getIncludeClientIds();
        //黑名单
        List<String> exclusiveClientIds = securityProperties.getAuth().getUrlPermission().getExclusiveClientIds();
        if (includeClientIds.size() > 0) {
            result = includeClientIds.contains(clientId);
        } else if (exclusiveClientIds.size() > 0) {
            result = !exclusiveClientIds.contains(clientId);
        }
        return result;
    }
}
