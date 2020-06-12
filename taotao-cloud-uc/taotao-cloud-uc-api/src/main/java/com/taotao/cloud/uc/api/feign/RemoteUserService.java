package com.taotao.cloud.uc.api.feign;

import com.taotao.cloud.common.constant.ServiceNameConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.uc.api.feign.fallback.RemoteUserFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用用户模块
 *
 * @author dengtao
 * @date 2020/5/2 16:42
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstant.TAOTAO_CLOUD_UC_CENTER, fallbackFactory = RemoteUserFallbackImpl.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户包括角色权限等
     *
     * @param username 用户名
     * @return com.taotao.cloud.common.model.Result<com.taotao.cloud.uc.api.dto.UserDetailsInfo>
     * @author dengtao
     * @date 2020/4/29 17:48
     */
    @GetMapping("/user/info/{username}")
    Result<SecurityUser> getUserInfoByUsername(@PathVariable("username") String username);

    /**
     * 通过手机号码查询用户包括角色权限等
     *
     * @param mobile 手机号码
     * @return com.taotao.cloud.common.model.Result<com.taotao.cloud.uc.api.dto.UserDetailsInfo>
     * @author dengtao
     * @date 2020/4/29 17:48
     */
    @GetMapping("/user/info/{mobile}")
    Result<SecurityUser> getUserInfoByMobile(@PathVariable("mobile") String mobile);

    /**
     * 通过第三方查询用户包括角色权限等
     *
     * @param providerId     providerId
     * @param providerUserId providerUserId
     * @return com.taotao.cloud.common.model.Result<com.taotao.cloud.uc.api.dto.UserDetailsInfo>
     * @author dengtao
     * @date 2020/4/29 17:47
     */
    @GetMapping("/user/info/social")
    Result<SecurityUser> getUserInfoBySocial(@RequestParam("providerId") String providerId,
                                             @RequestParam("providerUserId") int providerUserId);

}

