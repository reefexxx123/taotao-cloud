package com.taotao.cloud.auth.handler;

import com.taotao.cloud.common.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OauthLogoutSuccessHandler
 *
 * @author dengtao
 * @date 2020/4/29 21:22
 */
@Slf4j
public class OauthLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseUtil.success(response, "登出成功");
    }
}
