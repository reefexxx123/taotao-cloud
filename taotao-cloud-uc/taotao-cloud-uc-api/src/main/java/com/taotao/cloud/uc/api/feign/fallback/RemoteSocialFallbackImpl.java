package com.taotao.cloud.uc.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.uc.api.entity.SysGiteeUser;
import com.taotao.cloud.uc.api.entity.SysGithubUser;
import com.taotao.cloud.uc.api.entity.SysQqUser;
import com.taotao.cloud.uc.api.feign.RemoteSocialService;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * RemoteSocialFallbackImpl
 *
 * @author dengtao
 * @date 2020/4/29 21:43
 */
@Slf4j
@AllArgsConstructor
@Component
public class RemoteSocialFallbackImpl implements FallbackFactory<RemoteSocialService> {
    @Override
    public RemoteSocialService create(Throwable throwable) {
        return new RemoteSocialService() {

            @Override
            public Result<Boolean> saveGithubUser(SysGithubUser sysGithubUser) {
                log.error("调用saveGithubUser异常：{}", sysGithubUser, throwable);
                return Result.failed(null);
            }

            @Override
            public Result<Boolean> saveGiteeUser(SysGiteeUser sysGiteeUser) {
                log.error("调用saveGiteeUser异常：{}", sysGiteeUser, throwable);
                return Result.failed(null);
            }

            @Override
            public Result<Boolean> saveQqUser(SysQqUser sysQqUser) {
                log.error("调用saveQqUser异常：{}", sysQqUser, throwable);
                return Result.failed(null);
            }
        };
    }
}
