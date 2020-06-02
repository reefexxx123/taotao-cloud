package com.taotao.cloud.common.enums;

/**
 * 用户锁定类型  1-正常，2-锁定
 *
 * @author dengtao
 * @date 2020/4/30 10:25
 */
public enum LockFlagEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 锁定
     */
    LOCKED(2, "锁定");

    private int value;
    private String description;

    LockFlagEnum(int value, String description) {
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
