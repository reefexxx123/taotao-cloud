package com.taotao.cloud.ribbon.filter;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.context.LbIsolationContextHolder;
import com.taotao.cloud.ribbon.properties.RibbonIsolationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 负载均衡隔离规则过滤器
 *
 * @author dengtao
 * @date 2019/9/15
 */
@ConditionalOnClass(Filter.class)
public class LbIsolationFilter extends OncePerRequestFilter {

    @Autowired
    private RibbonIsolationProperties ribbonIsolationProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !ribbonIsolationProperties.isEnabled();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            String version = request.getHeader(CommonConstant.T_T_VERSION);
            if (StrUtil.isNotEmpty(version)) {
                LbIsolationContextHolder.setVersion(version);
            }

            filterChain.doFilter(request, response);
        } finally {
            LbIsolationContextHolder.clear();
        }
    }
}
