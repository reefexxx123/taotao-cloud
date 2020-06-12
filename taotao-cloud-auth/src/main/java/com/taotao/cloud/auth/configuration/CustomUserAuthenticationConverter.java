/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.auth.configuration
 * Date: 2020/6/12 09:15
 * Author: dengtao
 */
package com.taotao.cloud.auth.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/6/12 09:15
 */
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(USERNAME, authentication.getPrincipal());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
