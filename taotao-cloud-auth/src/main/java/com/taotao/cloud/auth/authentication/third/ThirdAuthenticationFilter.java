package com.taotao.cloud.auth.authentication.third;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.auth.enums.LoginType;
import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.token.ThirdAuthenticationToken;
import com.taotao.cloud.auth.util.LoginTypeUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第三方登录过滤器
 *
 * @author dengtao
 * @date 2020/4/29 19:58
 */
public class ThirdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    ThirdAuthenticationFilter() {
        super(new AntPathRequestMatcher("/oauth/token/third", HttpMethod.GET.toString()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (StrUtil.isBlank(code) && StrUtil.isBlank(state)) {
            throw new InvalidException("第三方认证参数错误");
        }
        LoginType loginType = LoginTypeUtil.getLoginType(state);
        ThirdAuthenticationToken authRequest = new ThirdAuthenticationToken(code, loginType);
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
