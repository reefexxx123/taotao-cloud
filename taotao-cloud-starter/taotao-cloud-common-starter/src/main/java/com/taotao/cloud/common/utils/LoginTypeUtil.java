package com.taotao.cloud.common.utils;


import com.taotao.cloud.common.enums.LoginTypeEnum;
import com.taotao.cloud.common.constant.SecurityConstant;

/**
 * LoginTypeUtil
 *
 * @author dengtao
 * @date 2020/5/2 16:40
 */
public class LoginTypeUtil {

    public static LoginTypeEnum getLoginType(String state) {
        switch (state) {
            case SecurityConstant.LOGIN_QQ:
                return LoginTypeEnum.qq;
            case SecurityConstant.LOGIN_WEIXIN:
                return LoginTypeEnum.weixin;
            case SecurityConstant.LOGIN_GITEE:
                return LoginTypeEnum.gitee;
            case SecurityConstant.SMS:
                return LoginTypeEnum.sms;
            case SecurityConstant.LOGIN_GITHUB:
                return LoginTypeEnum.github;
            default:
                return LoginTypeEnum.normal;
        }
    }
}
