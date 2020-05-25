package com.taotao.cloud.auth.authentication.openid;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.token.OpenIdAuthenticationToken;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * OpenId登录过滤
 *
 * @author dengtao
 * @date 2020/4/29 19:58
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher("/oauth/token/openId", HttpMethod.GET.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String openId = request.getParameter("openId");
        if (StrUtil.isBlank(openId)) {
            throw new InvalidException("openId不能为空");
        }
        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
