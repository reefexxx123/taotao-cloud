package com.taotao.cloud.auth.authentication.mobile;

import com.taotao.cloud.auth.exception.ValidateCodeException;
import com.taotao.cloud.auth.properties.SecurityProperties;
import com.taotao.cloud.auth.service.ISmsCodeService;
import com.taotao.cloud.auth.util.AuthUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机验证码过滤器
 *
 * @author dengtao
 * @date 2020/4/29 20:27
 */
@Slf4j
@Component
@AllArgsConstructor
public class MobileFilter extends OncePerRequestFilter {

    private final ISmsCodeService smsCodeService;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final SecurityProperties securityProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 返回true代表不执行过滤器，false代表执行
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 登录提交的时候验证验证码
        if (pathMatcher.match("/oauth/token/mobile", request.getRequestURI())) {
            // 判断是否有不验证验证码的client
            if (securityProperties.getCode().getIgnoreClientCode().length > 0) {
                try {
                    final String[] clientInfos = AuthUtils.extractClient(request);
                    String clientId = clientInfos[0];
                    for (String client : securityProperties.getCode().getIgnoreClientCode()) {
                        if (client.equals(clientId)) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    log.error("解析client信息失败", e);
                }
            }
            return false;
        }
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            smsCodeService.validate(request);
        } catch (ValidateCodeException e) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
