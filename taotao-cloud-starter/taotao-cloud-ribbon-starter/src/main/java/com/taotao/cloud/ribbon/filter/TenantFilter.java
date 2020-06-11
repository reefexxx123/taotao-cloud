package com.taotao.cloud.ribbon.filter;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.context.TenantContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 租户过滤器
 *
 * @author zlt
 * @date 2019/9/15
 */
@ConditionalOnClass(Filter.class)
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            //优先获取请求参数中的tenantId值
            String tenantId = request.getParameter(CommonConstant.TENANT_ID_PARAM);
            if (StrUtil.isEmpty(tenantId)) {
                tenantId = request.getHeader(SecurityConstant.TENANT_HEADER);
            }

            //保存租户id
            if (StrUtil.isNotEmpty(tenantId)) {
                TenantContextHolder.setTenant(tenantId);
            }
            ServletRequestAttributes attributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            RequestContextHolder.setRequestAttributes(attributes,true);

            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}
