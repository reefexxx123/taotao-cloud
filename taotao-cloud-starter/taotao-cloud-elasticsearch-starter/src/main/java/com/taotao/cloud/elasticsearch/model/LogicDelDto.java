package com.taotao.cloud.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 逻辑删除条件对象
 *
 * @author dengtao
 * @date 2020/5/3 07:49
*/
@Setter
@Getter
@AllArgsConstructor
public class LogicDelDto {
    /**
     * 逻辑删除字段名
     */
    private String logicDelField;
    /**
     * 逻辑删除字段未删除的值
     */
    private String logicNotDelValue;
}
