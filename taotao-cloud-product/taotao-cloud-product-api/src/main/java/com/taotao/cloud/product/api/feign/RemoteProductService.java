package com.taotao.cloud.product.api.feign;

import com.taotao.cloud.common.constant.ServiceNameConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.product.api.feign.fallback.RemoteProdcutFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用商品模块
 *
 * @author dengtao
 * @date 2020/5/2 16:42
 */
@FeignClient(contextId = "remoteProductService", value = ServiceNameConstant.TAOTAO_CLOUD_PRODUCT_CENTER, fallbackFactory = RemoteProdcutFallbackImpl.class)
public interface RemoteProductService {

    /**
     * 通过id查询商品信息
     *
     * @param id id
     * @return com.taotao.cloud.common.model.Result<java.lang.String>
     * @author dengtao
     * @date 2020/6/10 11:27
     */
    @GetMapping("/info/{id}")
    Result<String> getProductInfoById(@PathVariable("id") String id);
}

