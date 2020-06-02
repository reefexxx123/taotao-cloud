/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.common.enums
 * Descroption:
 * Date: 2020/5/12 16:28
 * Author: dengtao
 */
package com.taotao.cloud.common.enums;

/**
 * 目录类型枚举
 *
 * @author dengtao
 * @date 2020/6/2 15:37
 */
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0, "目录"),
    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(2, "按钮");

    private int value;
    private String description;

    MenuTypeEnum(int value, String description) {
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
