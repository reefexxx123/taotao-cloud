package com.taotao.cloud.auth.filter;

import cn.hutool.core.util.ArrayUtil;
import com.taotao.cloud.common.context.TenantContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置租户id过滤器
 *
 * @author dengtao
 * @date 2020/4/29 21:16
 */
public class LoginProcessSetTenantFilter extends OncePerRequestFilter {
    private static final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

    private final RequestMatcher requiresAuthenticationRequestMatcher;

    public LoginProcessSetTenantFilter() {
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher("/user/login", HttpMethod.POST.name());
    }

    /**
     * 返回true代表不执行过滤器，false代表执行
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !requiresAuthentication(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            DefaultSavedRequest savedRequest = (DefaultSavedRequest) request.getSession().getAttribute(SAVED_REQUEST);
            if (savedRequest != null) {
                String[] clientIds = savedRequest.getParameterValues("client_id");
                if (ArrayUtil.isNotEmpty(clientIds)) {
                    //保存租户id
                    TenantContextHolder.setTenant(clientIds[0]);
                }
            }
            chain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }

    private boolean requiresAuthentication(HttpServletRequest request) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }
}
