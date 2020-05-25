package com.taotao.cloud.auth.authentication.mobile;

import com.taotao.cloud.auth.exception.InvalidException;
import com.taotao.cloud.auth.token.MobileAuthenticationToken;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 手机认证过滤器
 * <p>
 * 1.认证请求的方法必须为POST
 * 2.从request中获取手机号
 * 3.封装成自己的Authentication的实现类SmsCodeAuthenticationToken（未认证）
 * 4.调用 AuthenticationManager 的 authenticate 方法进行验证（即MobileAuthenticationProvider
 *
 * @author dengtao
 * @date 2020/4/29 20:23
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 处理的手机验证码登录请求处理url
     */
    MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher("/oauth/token/mobile", HttpMethod.GET.toString()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String mobile = request.getParameter("mobile");
        if (mobile == null) {
            throw new InvalidException("手机号码不能为空");
        }
        mobile = mobile.trim();
        MobileAuthenticationToken mobileToken = new MobileAuthenticationToken(mobile);
        mobileToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(mobileToken);
    }
}
