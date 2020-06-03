package com.taotao.cloud.elk;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.utils.JsonUtil;
import com.taotao.cloud.elk.properties.ElkHealthLogStatisticProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面获取入参和出参
 *
 * @author: dengtao
 * @version: 2019-08-21 14:49
 */
@Slf4j
@Aspect
public class WebControllerAspect {

    private static final String[] tokenKeys = {SecurityConstant.BASE_AUTHORIZED, SecurityConstant.AUTHORIZED};

    @Pointcut("@within(org.springframework.stereotype.Controller) " +
            "|| @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {

    }

    @Autowired
    private ElkHealthLogStatisticProperties logStatisticProperties;

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        Exception exception = null;
        Object result = null;
        long timeSpan = 0;

        HttpServletRequest request = RequestContextHolder.getRequestAttributes() == null ? null : ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            long start = System.currentTimeMillis();
            result = joinPoint.proceed();
            timeSpan = System.currentTimeMillis() - start;
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            if (logStatisticProperties.isEnabled()) {
                if (request != null) {
                    String uri = request.getRequestURI().replace(request.getContextPath(), "");
                    String inPutParam = preHandle(joinPoint, request);
                    String outPutParam = postHandle(result);
                    String ip = getRemoteHost(request);
                    log.info("【远程ip】{},【url】{},【输入】{},【输出】{},【异常】{},【耗时】{}ms", ip, uri, inPutParam, outPutParam,
                            exception == null ? "无" : StrUtil.nullToEmpty(exception.getMessage()), timeSpan);
                }
            }
        }

        return result;
    }

    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        StringBuilder sb = new StringBuilder();

        for (val tokenKey : tokenKeys) {
            String token = request.getHeader(tokenKey);
            if (StringUtils.isNotBlank(token)) {
                sb.append("token:").append(token).append(",");
                break;
            }
        }

        for (Annotation annotation : annotations) {
            if (!annotation.annotationType().toString().contains("org.springframework.web.bind.annotation")) {
                continue;
            }
            sb.append(JsonUtil.serialize(request.getParameterMap()));
        }
        return sb.toString();
    }

    /**
     * 返回数据
     *
     * @param retVal retVal
     */
    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }
        return JsonUtil.serialize(retVal);
    }

    private String getRemoteHost(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}
