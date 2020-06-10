/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.order.biz.service.impl
 * Date: 2020/6/10 16:55
 * Author: dengtao
 */
package com.taotao.cloud.order.biz.service.impl;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.order.biz.service.IOrderInfoService;
import com.taotao.cloud.product.api.feign.RemoteProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/6/10 16:55
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private RemoteProductService remoteProductService;

    @Override
    public String getOrderInfoById(String id) {
        Result<String> productInfoResult = remoteProductService.getProductInfoById(id);
        String data = productInfoResult.getData();
        log.info("******************************* " + data);
        return "hello woshi orderINFO--------" + id + "----" + data;
    }
}
