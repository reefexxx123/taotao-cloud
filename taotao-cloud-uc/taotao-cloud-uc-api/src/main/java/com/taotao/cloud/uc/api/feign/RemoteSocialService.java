package com.taotao.cloud.uc.api.feign;

import com.taotao.cloud.common.constant.ServiceNameConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.uc.api.entity.SysGiteeUser;
import com.taotao.cloud.uc.api.entity.SysGithubUser;
import com.taotao.cloud.uc.api.entity.SysQqUser;
import com.taotao.cloud.uc.api.feign.fallback.RemoteSocialFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程调用用户模块
 *
 * @author dengtao
 * @date 2020/5/2 16:42
 */
@FeignClient(contextId = "remoteSocialService", value = ServiceNameConstant.TAOTAO_CLOUD_UC_CENTER, fallbackFactory = RemoteSocialFallbackImpl.class)
public interface RemoteSocialService {

    /**
     * 添加github用户
     *
     * @param sysGithubUser
     * @return com.taotao.cloud.common.model.Result<java.lang.Boolean>
     * @author dengtao
     * @date 2020/5/14 15:31
     */
    @PostMapping("/backend/github")
    Result<Boolean> saveGithubUser(@RequestBody SysGithubUser sysGithubUser);


    /**
     * 添加gitee用户
     *
     * @param sysGiteeUser
     * @return com.taotao.cloud.common.model.Result<java.lang.Boolean>
     * @author dengtao
     * @date 2020/5/14 15:31
     */
    @PostMapping("/backend/gitee")
    Result<Boolean> saveGiteeUser(@RequestBody SysGiteeUser sysGiteeUser);

    /**
     * 添加gitee用户
     *
     * @param sysQqUser
     * @return com.taotao.cloud.common.model.Result<java.lang.Boolean>
     * @author dengtao
     * @date 2020/5/14 15:31
     */
    @PostMapping("/backend/qq")
    Result<Boolean> saveQqUser(@RequestBody SysQqUser sysQqUser);

}

