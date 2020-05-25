package com.taotao.cloud.elasticsearch.model;

import lombok.Data;

/**
 * 索引
 *
 * @author dengtao
 * @date 2020/5/3 07:48
 */
@Data
public class IndexDto {
    /**
     * 索引名
     */
    private String indexName;
    /**
     * 分片数 number_of_shards
     */
    private Integer numberOfShards;
    /**
     * 副本数 number_of_replicas
     */
    private Integer numberOfReplicas;
    /**
     * 类型
     */
    private String type;
    /**
     * mappings内容
     */
    private String mappingsSource;
}
