package com.taotao.cloud.log.listener;

import com.taotao.cloud.log.event.SysLogEvent;
import com.taotao.cloud.log.service.ISysLogService;
import com.taotao.cloud.uc.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 注解形式的监听 异步监听日志事件
 *
 * @author dengtao
 * @date 2020/6/3 13:33
 */
@Slf4j
public class SysLogListener {

    @Autowired
    private ISysLogService sysLogService;

    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        sysLogService.save(sysLog);
    }
}
