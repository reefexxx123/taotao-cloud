package com.taotao.cloud.auth.util;


import com.taotao.cloud.auth.enums.LoginType;
import com.taotao.cloud.common.constant.SecurityConstant;

/**
 * LoginTypeUtil
 *
 * @author dengtao
 * @date 2020/5/2 16:40
 */
public class LoginTypeUtil {

    public static LoginType getLoginType(String state) {
        switch (state) {
            case SecurityConstant.LOGIN_QQ:
                return LoginType.qq;
            case SecurityConstant.LOGIN_WEIXIN:
                return LoginType.weixin;
            case SecurityConstant.LOGIN_GITEE:
                return LoginType.gitee;
            case SecurityConstant.SMS:
                return LoginType.sms;
            case SecurityConstant.LOGIN_GITHUB:
                return LoginType.github;
            default:
                return LoginType.normal;
        }
    }
}
