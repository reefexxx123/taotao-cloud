package com.taotao.cloud.elk;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.marker.MapEntriesAppendingMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ElkWebInterceptor
 *
 * @author dengtao
 * @date 2020/6/3 10:49
*/
public class ElkWebInterceptor implements HandlerInterceptor {

    private final ThreadLocal<Long> local = new ThreadLocal<>();

    @Autowired(required = false)
    private LogstashTcpSocketAppender logstashTcpSocketAppender;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (logstashTcpSocketAppender != null && handler instanceof HandlerMethod) {
            local.set(System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Long startTime = local.get();
        if (startTime == null) {
            return;
        }
        local.remove();
        long costTime = System.currentTimeMillis() - startTime;
        String path = httpServletRequest.getRequestURI();
        HandlerMethod handler = (HandlerMethod) o;
        Map<String, Object> values = new HashMap<>();
        values.put("logger_type", "api");
        values.put("service", handler.getBeanType().getName());
        values.put("method", handler.getBeanType().getName() + "." + handler.getMethod().getName());
        values.put("path", httpServletRequest.getRequestURI());
        values.put("cost_time", costTime);
        values.put("result", e == null);
        values.put("result_message", e == null ? "success" : e.getClass().getName() + ": " + e.getMessage());
        logstashTcpSocketAppender.doAppend(createLoggerEvent(values, path + ": " + costTime));
    }

    private LoggingEvent createLoggerEvent(Map<String, Object> values, String message) {
        LoggingEvent loggingEvent = new LoggingEvent();
        loggingEvent.setTimeStamp(System.currentTimeMillis());
        loggingEvent.setLevel(Level.INFO);
        loggingEvent.setLoggerName("ElkLogger");
        loggingEvent.setMarker(new MapEntriesAppendingMarker(values));
        loggingEvent.setMessage(message);
        loggingEvent.setArgumentArray(new String[0]);
        loggingEvent.setThreadName(Thread.currentThread().getName());
        return loggingEvent;
    }
}
