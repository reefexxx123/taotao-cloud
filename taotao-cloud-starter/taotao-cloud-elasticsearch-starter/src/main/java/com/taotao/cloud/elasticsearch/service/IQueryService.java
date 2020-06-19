package com.taotao.cloud.elasticsearch.service;

import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.LogicDelDto;
import com.taotao.cloud.elasticsearch.model.SearchDto;

import java.io.IOException;
import java.util.Map;

/**
 * QueryService
 *
 * @author dengtao
 * @date 2020/5/3 08:01
*/
public interface IQueryService {
    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     */
    PageResult<String> strQuery(String indexName, SearchDto searchDto) throws IOException;

    /**
     * 查询文档列表
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     * @param logicDelDto 逻辑删除Dto
     */
    PageResult<String> strQuery(String indexName, SearchDto searchDto, LogicDelDto logicDelDto) throws IOException;

    /**
     * 访问统计聚合查询
     * @param indexName 索引名
     * @param routing es的路由
     */
    Map<String, Object> requestStatAgg(String indexName, String routing) throws IOException;
}
