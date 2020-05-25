package com.taotao.cloud.elasticsearch.service;


import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.IndexDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 索引service
 *
 * @author dengtao
 * @date 2020/5/3 08:01
*/
public interface IIndexService {
    /**
     * 创建索引
     */
    boolean create(IndexDto indexDto) throws IOException;

    /**
     * 删除索引
     * @param indexName 索引名
     */
    boolean delete(String indexName) throws IOException;

    /**
     * 索引列表
     * @param queryStr 搜索字符串
     * @param indices 默认显示的索引名
     */
    PageResult<HashMap<String, String>> list(String queryStr, String indices) throws IOException;

    /**
     * 显示索引明细
     * @param indexName 索引名
     */
    Map<String, Object> show(String indexName) throws IOException;
}
