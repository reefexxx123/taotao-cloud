package com.taotao.cloud.ribbon.filter;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志链路追踪过滤器
 *
 * @author dengtao
 * @date 2020/6/15 11:30
*/
@ConditionalOnClass(Filter.class)
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = request.getHeader(CommonConstant.TRACE_ID_HEADER);
            if (StrUtil.isNotEmpty(traceId)) {
                MDC.put(CommonConstant.LOG_TRACE_ID, traceId);
            }

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
