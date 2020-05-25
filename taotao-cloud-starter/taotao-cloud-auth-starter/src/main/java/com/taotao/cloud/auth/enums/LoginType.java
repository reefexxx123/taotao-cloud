package com.taotao.cloud.auth.enums;


import com.taotao.cloud.common.constant.SecurityConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录类型 现在有用户名 短信 社交
 *
 * @author dengtao
 * @date 2020/4/29 18:11
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 用户密码登录
     */
    normal(SecurityConstant.NORMAL, "用户密码登录"),
    /**
     * 短信密码登录
     */
    sms(SecurityConstant.SMS, "短信密码登录"),
    /**
     * 社交登录
     */
    qq(SecurityConstant.LOGIN_QQ, "qq登录"),
    weixin(SecurityConstant.LOGIN_WEIXIN, "微信登录"),
    gitee(SecurityConstant.LOGIN_GITEE, "gitee登录"),
    github(SecurityConstant.LOGIN_GITHUB, "github登录");

    /**
     * 登录类型
     */
    private final String type;
    /**
     * 功能描述
     */
    private final String description;
}
