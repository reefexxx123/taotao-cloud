package com.taotao.cloud.log.service;


import com.taotao.cloud.uc.api.entity.SysLog;

/**
 * 日志接口
 *
 * @author dengtao
 * @date 2020/5/2 11:18
*/
public interface ISysLogService {
    /**
     * 添加日志
     *
     * @param sysLog sysLog
     * @return void
     * @author dengtao
     * @date 2020/4/27 16:52
     */
    void save(SysLog sysLog);
}
