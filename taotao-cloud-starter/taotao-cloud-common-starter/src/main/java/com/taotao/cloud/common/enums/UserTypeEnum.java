package com.taotao.cloud.common.enums;

/**
 * 用户类型
 *
 * @author dengtao
 * @date 2020/4/30 10:25
 */
public enum UserTypeEnum {
    /**
     * 前端app用户
     */
    APP(1, "前端用户"),
    /**
     * 商户用户
     */
    COMPANY(2, "商户用户"),
    /**
     * 后台管理用户
     */
    BACKEND(3, "后台管理用户");

    private int value;
    private String description;

    UserTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
