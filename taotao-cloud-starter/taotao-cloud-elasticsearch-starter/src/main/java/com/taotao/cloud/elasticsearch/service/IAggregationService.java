package com.taotao.cloud.elasticsearch.service;

import java.io.IOException;
import java.util.Map;

/**
 * 聚合service
 *
 * @author dengtao
 * @date 2020/5/3 08:01
*/
public interface IAggregationService {
    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     */
    Map<String, Object> requestStatAgg(String indexName, String routing) throws IOException;
}
