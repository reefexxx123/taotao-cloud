package com.taotao.cloud.log.feign;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.feign.fallback.RemoteLogFallbackImpl;
import com.taotao.cloud.uc.api.entity.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * RemoteLogService
 *
 * @author dengtao
 * @date 2020/5/2 16:41
*/
@FeignClient(contextId = "remoteLogService", value = "taotao-cloud-uc-center", fallbackFactory = RemoteLogFallbackImpl.class)
public interface RemoteLogService {

    @PostMapping(value = "/log")
    Result<Boolean> saveLog(@RequestBody SysLog sysLog);
}
