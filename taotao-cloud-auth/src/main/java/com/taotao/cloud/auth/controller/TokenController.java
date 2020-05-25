package com.taotao.cloud.auth.controller;

import com.taotao.cloud.auth.dto.OAuth2AccessTokenDTO;
import com.taotao.cloud.auth.dto.UserTokenDTO;
import com.taotao.cloud.auth.model.TokenVo;
import com.taotao.cloud.auth.service.ITokensService;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * Token管理api
 *
 * @author dengtao
 * @date 2020/4/29 16:01
 */
@RestController
@RequestMapping("/oauth/token")
@Api(value = "Token管理API", tags = {"Token管理API"})
public class TokenController {

    @Autowired
    private ITokensService tokensService;

    @ApiOperation(value = "token列表")
    @GetMapping("/list")
    public Result<PageResult<TokenVo>> list(@RequestParam Map<String, Object> params, String tenantId) {
        return tokensService.listTokens(params, tenantId);
    }

    @ApiOperation(value = "用户名密码获取token")
    @GetMapping("/user")
    public Result<OAuth2AccessTokenDTO> getUserTokenInfo(@RequestParam(value = "username") String username,
                                                         @RequestParam(value = "username") String password,
                                                         HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        OAuth2AccessTokenDTO oAuth2AccessToken = tokensService.getToken(request, response, token);
        if (Objects.nonNull(oAuth2AccessToken)) {
            return Result.succeed(oAuth2AccessToken);
        }
        return Result.authenticationFailed("用户名或密码错误");
    }

//    @ApiOperation(value = "openId获取token")
//    @PostMapping("/oauth/openId/token")
//    public Result<OAuth2AccessToken> getTokenByOpenId(@RequestBody OpenIdTokenDTO openIdTokenDTO,
//            HttpServletRequest request, HttpServletResponse response) {
//        String openId = openIdTokenDTO.getOpenId();
//        Assert.notNull(openId, "openId must be set");
//        OpenIdAuthenticationToken token = new OpenIdAuthenticationToken(openId);
//        OAuth2AccessToken oAuth2AccessToken = tokensService.getToken(request, response, token);
//        if (Objects.nonNull(oAuth2AccessToken)) {
//            return Result.succeed(oAuth2AccessToken);
//        }
//        return Result.authenticationFailed("openId错误");
//    }
//
//    @ApiOperation(value = "mobile获取token")
//    @PostMapping("/oauth/mobile/token")
//    public Result<OAuth2AccessToken> getTokenByMobile(@RequestBody MobileTokenDTO mobileTokenDTO,
//            HttpServletRequest request, HttpServletResponse response) {
//        String mobile = mobileTokenDTO.getMobile();
//        String password = mobileTokenDTO.getPassword();
//        Assert.notNull(mobile, "mobile must be set");
//        Assert.notNull(password, "password must be set");
//        MobileAuthenticationToken token = new MobileAuthenticationToken(mobile, password);
//        OAuth2AccessToken oAuth2AccessToken = tokensService.getToken(request, response, token);
//        if (Objects.nonNull(oAuth2AccessToken)) {
//            return Result.succeed(oAuth2AccessToken);
//        }
//        return Result.authenticationFailed("手机号或密码错误");
//    }

}
