package com.taotao.cloud.order.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.order.api.feign.RemoteOrderService;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * RemoteLogFallbackImpl
 *
 * @author dengtao
 * @date 2020/4/29 21:43
 */
@Slf4j
@AllArgsConstructor
@Component
public class RemoteOrderFallbackImpl implements FallbackFactory<RemoteOrderService> {
    @Override
    public RemoteOrderService create(Throwable throwable) {
        return new RemoteOrderService() {
            @Override
            public Result<String> getOrderInfoById(String id) {
                log.error("getOrderInfoById：{}", id, throwable);
                return Result.failed(null);
            }
        };
    }
}
