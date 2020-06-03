package com.taotao.cloud.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限枚举
 *
 * @author dengtao
 * @date 2020/5/2 16:40
 */
public enum DataScopeTypeEnum {

    /**
     * 全部
     */
    ALL(1, "全部"),
    /**
     * 本级
     */
    THIS_LEVEL(2, "本级"),

    /**
     * 本级以及子级
     */
    THIS_LEVEL_CHILDREN(3, "本级以及子级"),
    /**
     * 自定义
     */
    CUSTOMIZE(4, "自定义");


    private int type;
    private String description;


    public static DataScopeTypeEnum valueOf(int type) {
        for (DataScopeTypeEnum typeVar : DataScopeTypeEnum.values()) {
            if (typeVar.getType() == type) {
                return typeVar;
            }
        }
        return ALL;
    }

    DataScopeTypeEnum(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
