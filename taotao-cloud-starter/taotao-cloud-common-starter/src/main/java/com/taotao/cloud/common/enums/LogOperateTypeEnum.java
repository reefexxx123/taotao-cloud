package com.taotao.cloud.common.enums;

/**
 * 日志类型
 *
 * @author dengtao
 * @date 2020/4/30 10:25
 */
public enum LogOperateTypeEnum {
    /**
     * 操作记录
     */
    OPERATE_RECORD(1, "操作记录"),

    /**
     * 异常记录
     */
    EXCEPTION_RECORD(2, "异常记录");

    private int value;
    private String description;

    LogOperateTypeEnum(int value, String description) {
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
