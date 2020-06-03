package com.taotao.cloud.log.event;

import com.taotao.cloud.uc.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author dengtao
 * @date 2020/6/3 13:33
*/
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog sysLog) {
        super(sysLog);
    }
}
