package com.taotao.cloud.product.biz.controller;


import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.SysOperateLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理API
 *
 * @author dengtao
 * @date 2020/4/30 11:03
 */
@Slf4j
@RestController
@RequestMapping("/info")
@Api(value = "商品管理API", tags = { "商品管理API" })
public class ProductController {

    @ApiOperation("根据id查询商品信息")
    @GetMapping("/{id}")
    @SysOperateLog(description = "根据id查询商品信息")
    @PreAuthorize("hasAuthority('sys:user:view')")
    public Result<String> add(@PathVariable(value = "id") Integer id) {
        log.info("******************* : " + id);
        return Result.succeed("******************* : " + id);
    }

}

