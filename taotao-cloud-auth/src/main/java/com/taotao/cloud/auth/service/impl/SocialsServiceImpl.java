/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.auth.service.impl
 * Date: 2020/5/13 21:43
 * Author: dengtao
 */
package com.taotao.cloud.auth.service.impl;

import com.taotao.cloud.auth.enums.LoginType;
import com.taotao.cloud.auth.service.ISocialsService;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.social.service.GitHubService;
import com.taotao.cloud.social.service.GiteeService;
import com.taotao.cloud.social.service.QQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/13 21:43
 */
@Slf4j
@Service
public class SocialsServiceImpl implements ISocialsService {
    @Resource
    private GiteeService giteeService;
    @Resource
    private GitHubService gitHubService;
    @Resource
    private QQService qqService;

    @Override
    public String getAuthUrl(String loginType) {
        switch (loginType) {
            case SecurityConstant.LOGIN_GITEE:
                return giteeService.getAuthUrl();
            case SecurityConstant.LOGIN_QQ:
                return qqService.getQqAuthUrl();
            case SecurityConstant.LOGIN_GITHUB:
                return gitHubService.getGithubAuthUrl();
            default:
                return "";
        }
    }
}
