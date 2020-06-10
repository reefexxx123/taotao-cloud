package com.taotao.cloud.product.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.product.api.feign.RemoteProductService;
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
public class RemoteProdcutFallbackImpl implements FallbackFactory<RemoteProductService> {
    @Override
    public RemoteProductService create(Throwable throwable) {
        return new RemoteProductService() {
            @Override
            public Result<String> getProductInfoById(String id) {
                log.error("getProductInfoById：{}", id, throwable);
                return Result.failed(null);
            }
        };
    }
}
