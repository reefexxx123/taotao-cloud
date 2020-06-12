package com.taotao.cloud.auth.utils;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.common.utils.GsonUtil;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

/**
 * 安全服务工具类
 *
 * @author dengtao
 * @date 2020/4/30 10:39
 */
@UtilityClass
public class SecurityUtil {

    public void writeJavaScript(Result result, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(result));
        printWriter.flush();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @param authentication authentication
     * @return com.taotao.cloud.auth.model.SecurityUser
     * @author dengtao
     * @date 2020/4/30 10:56
     */
    public SecurityUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser) {
            return (SecurityUser) principal;
        }else if(principal instanceof Map){
            return GsonUtil.gson().fromJson(GsonUtil.toGson(principal), SecurityUser.class);
        }
        return null;
    }

    public SecurityUser getUser() {
        Authentication authentication = getAuthentication();
        return getUser(authentication);
    }

    public String getUsername() {
        SecurityUser user = getUser();
        return Objects.isNull(user) ? "" : user.getUsername();
    }

    public String getUserId() {
        SecurityUser user = getUser();
        return Objects.isNull(user) ? "" : String.valueOf(user.getUserId());
    }

    public String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            return auth2Authentication.getOAuth2Request().getClientId();
        }
        return null;
    }
}
