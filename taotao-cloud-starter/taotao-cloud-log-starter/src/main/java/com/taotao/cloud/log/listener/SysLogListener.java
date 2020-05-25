package com.taotao.cloud.log.listener;

import com.taotao.cloud.log.event.SysLogEvent;
import com.taotao.cloud.log.service.ISysLogService;
import com.taotao.cloud.uc.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @Classname SysLogListener
 * @Description 注解形式的监听 异步监听日志事件
 * @Author 李号东 im.lihaodong@gmail.com
 * @Date 2019-04-28 11:34
 * @Version 1.0
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
        log.info("远程日志记录成功：{}", sysLog);
    }
}
