package com.taotao.cloud.common.enums;


/**
 * 用户逻辑删除类型  1-正常，2-锁定
 *
 * @author dengtao
 * @date 2020/4/30 10:25
 */
public enum DelFlagEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 删除
     */
    DELETE(2, "删除");

    private int value;
    private String description;

    DelFlagEnum(int value, String description) {
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
