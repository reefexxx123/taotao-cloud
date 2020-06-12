package com.taotao.cloud.file.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.file.api.feign.RemoteFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * RemoteLogFallbackImpl
 *
 * @author dengtao
 * @date 2020/4/29 21:43
 */
@Slf4j
@AllArgsConstructor
@Component
public class RemoteFileFallbackImpl implements RemoteFileService {

    @Override
    public Result<SecurityUser> getUserInfoByUsername(String username) {
        log.error("feign 调用用户{}", username);
        return null;
    }

    @Override
    public Result<SecurityUser> getUserInfoByMobile(String mobile) {
        log.error("feign 调用用户手机号码{}", mobile);
        return null;
    }

    @Override
    public Result<SecurityUser> getUserInfoBySocial(String providerId, int providerUserId) {
        log.error("feign 调用用户{}", providerUserId);
        return null;
    }
}
