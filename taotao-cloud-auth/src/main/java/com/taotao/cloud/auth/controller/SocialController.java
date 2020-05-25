package com.taotao.cloud.auth.controller;

import com.taotao.cloud.auth.service.ISocialsService;
import com.taotao.cloud.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方登录API
 *
 * @author dengtao
 * @date 2020/4/29 16:07
 */
@RestController
@Api(value = "第三方登录API", tags = {"第三方登录API"})
@RequestMapping(value = "/social")
public class SocialController {

    @Autowired
    private ISocialsService socialsService;

    @ApiOperation(value = "获取认证url")
    @GetMapping("/url")
    public Result<String> getAuthUrl(@RequestParam(value = "loginType") String loginType) {
        return Result.succeed(socialsService.getAuthUrl(loginType));
    }
}
