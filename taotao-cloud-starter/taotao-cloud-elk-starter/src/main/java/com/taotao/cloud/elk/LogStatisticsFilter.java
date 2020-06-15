package com.taotao.cloud.elk;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 错误日志统计拦截器
 *
 * @author dengtao
 * @date 2020/6/15 11:28
*/
public class LogStatisticsFilter extends AbstractMatcherFilter<ILoggingEvent> {

    private static long lastCollectTime = System.currentTimeMillis() / 60000;
    private static volatile AtomicLong errorCount = new AtomicLong(0);
    private static volatile AtomicLong logCount = new AtomicLong(0);

    @Override
    public FilterReply decide(ILoggingEvent event) {
        logCount.incrementAndGet();
        if (event.getLevel().equals(Level.ERROR)) {
            errorCount.incrementAndGet();
        }
        if (System.currentTimeMillis() / 60000 > lastCollectTime) {
            lastCollectTime = System.currentTimeMillis() / 60000;
            logCount.set(0);
            errorCount.set(0);
        }

        return FilterReply.NEUTRAL;
    }
}
