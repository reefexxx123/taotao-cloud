package com.taotao.cloud.log.feign.fallback;

import com.taotao.cloud.log.feign.RemoteLogService;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * RemoteLogFallbackImpl
 *
 * @author dengtao
 * @date 2020/5/2 16:41
 */
@Slf4j
public class RemoteLogFallbackImpl implements FallbackFactory<RemoteLogService> {

    @Override
    public RemoteLogService create(Throwable throwable) {
        return sysLog -> {
            log.error("保存日志失败:{}", sysLog, throwable);
            return null;
        };
    }
}
