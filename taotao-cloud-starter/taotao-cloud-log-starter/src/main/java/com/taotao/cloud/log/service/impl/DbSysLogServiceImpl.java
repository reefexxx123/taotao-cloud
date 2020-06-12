package com.taotao.cloud.log.service.impl;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.feign.RemoteLogService;
import com.taotao.cloud.log.service.ISysLogService;
import com.taotao.cloud.uc.api.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 审计日志实现类-打印日志
 *
 * @author dengtao
 * @date 2020/5/2 11:18
 */
@Slf4j
public class DbSysLogServiceImpl implements ISysLogService {

    @Autowired
    private RemoteLogService remoteLogService;

    @Override
    public void save(SysLog sysLog) {
        Result<Boolean> result = remoteLogService.saveLog(sysLog);
        Boolean data = result.getData();
        if (data) {
            log.info("数据库远程日志记录成功：{}", sysLog);
        } else {
            log.error("数据库远程日志记录失败：{}", sysLog);
        }
    }
}
