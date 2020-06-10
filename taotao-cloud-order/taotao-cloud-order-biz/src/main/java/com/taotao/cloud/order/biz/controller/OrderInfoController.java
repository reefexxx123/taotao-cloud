package com.taotao.cloud.order.biz.controller;


import com.taotao.cloud.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation("获取订单信息")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public Result<String> getDeptList(@PathVariable(value = "id") String id) {
        log.info("*******************************");
        return Result.succeed("hello woshi orderINFO--------" + id);
    }

}

