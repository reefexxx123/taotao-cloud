package com.taotao.cloud.auth.controller;

import com.taotao.cloud.auth.service.ISmsCodeService;
import com.taotao.cloud.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 手机验证码管理API
 *
 * @author dengtao
 * @date 2020/4/29 16:07
 */
@Controller
@Api(value = "手机验证码管理API", tags = {"手机验证码管理API"})
public class SmsCodeController {

    @Autowired
    private ISmsCodeService validateCodeService;

    @ResponseBody
    @ApiOperation(value = "发送手机验证码 后期要加接口限制")
    @GetMapping("/sms/code")
    public Result<Boolean> createCode(@RequestParam(value = "mobile") String mobile) {
        return validateCodeService.sendSmsCode(mobile);
    }
}
