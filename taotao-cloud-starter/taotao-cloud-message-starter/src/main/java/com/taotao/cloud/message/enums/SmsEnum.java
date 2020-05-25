package com.taotao.cloud.message.enums;

/**
 * 返回枚举
 *
 * @author dengtao
 * @date 2020/4/29 15:36
 */
public enum SmsEnum {

    /**
     * 登录
     */
    LOGIN("login"),

    /**
     * 注册
     */
    REGISTER("register");

    /**
     * 类型
     */
    private final String type;

    SmsEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
