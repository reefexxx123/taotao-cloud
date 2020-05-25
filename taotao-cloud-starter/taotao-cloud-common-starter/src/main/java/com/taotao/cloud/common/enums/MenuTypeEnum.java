/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.common.enums
 * Descroption:
 * Date: 2020/5/12 16:28
 * Author: dengtao
 */
package com.taotao.cloud.common.enums;

/**
 * 〈〉<br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/12 16:28
 * @see
 * @since v1.0.0
 */
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
