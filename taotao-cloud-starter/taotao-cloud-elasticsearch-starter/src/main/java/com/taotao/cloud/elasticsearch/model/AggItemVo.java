package com.taotao.cloud.elasticsearch.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 聚合Item
 *
 * @author dengtao
 * @date 2020/5/3 07:48
*/
@Setter
@Getter
public class AggItemVo {
    private String name;
    private Long value;
}
