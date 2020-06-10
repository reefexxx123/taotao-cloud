package com.taotao.cloud.order.api.feign;

import com.taotao.cloud.common.constant.ServiceNameConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.order.api.feign.fallback.RemoteOrderFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用用户模块
 *
 * @author dengtao
 * @date 2020/5/2 16:42
 */
@FeignClient(contextId = "remoteOrderService", value = ServiceNameConstant.TAOTAO_CLOUD_ORDER_CENTER, fallbackFactory = RemoteOrderFallbackImpl.class)
public interface RemoteOrderService {

    /**
     * 通过id查询订单信息
     *
     * @param id id
     * @return com.taotao.cloud.common.model.Result<java.lang.String>
     * @author dengtao
     * @date 2020/6/10 11:27
     */
    @GetMapping("/info/{id}")
    Result<String> getOrderInfoById(@PathVariable("id") String id);
}

