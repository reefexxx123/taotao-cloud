package com.taotao.cloud.order.biz.controller;


import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.SysOperateLog;
import com.taotao.cloud.order.biz.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理API
 *
 * @author dengtao
 * @date 2020/4/30 11:03
 */
@Slf4j
@RestController
@RequestMapping("/info")
@Api(value = "订单管理API", tags = {"订单管理API"})
public class OrderInfoController {
    @Autowired
    private IOrderInfoService orderInfoService;

    @ApiOperation("获取订单信息")
    @GetMapping("/{id}")
    @SysOperateLog(description = "lsdfjalskdfl")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public Result<String> getDeptList(@PathVariable(value = "id") String id) {
        String orderInfo = orderInfoService.getOrderInfoById(id);
        return Result.succeed(orderInfo);
    }

}

