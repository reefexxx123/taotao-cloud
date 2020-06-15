package com.taotao.cloud.elasticsearch.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.LogicDelDto;
import com.taotao.cloud.elasticsearch.model.SearchDto;
import com.taotao.cloud.elasticsearch.service.IAggregationService;
import com.taotao.cloud.elasticsearch.service.IQueryService;
import com.taotao.cloud.elasticsearch.service.ISearchService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 搜索客户端Service
 *
 * @author dengtao
 * @date 2020/6/15 11:27
*/
public class QueryServiceImpl implements IQueryService {
    @Resource
    private ISearchService searchService;

    @Resource
    private IAggregationService aggregationService;

    @Override
    public PageResult<JSONObject> strQuery(String indexName, SearchDto searchDto) throws IOException {
        return strQuery(indexName, searchDto, null);
    }

    @Override
    public PageResult<JSONObject> strQuery(String indexName, SearchDto searchDto, LogicDelDto logicDelDto) throws IOException {
        setLogicDelQueryStr(searchDto, logicDelDto);
        return searchService.strQuery(indexName, searchDto);
    }

    /**
     * 拼装逻辑删除的条件
     *
     * @param searchDto   搜索dto
     * @param logicDelDto 逻辑删除dto
     */
    private void setLogicDelQueryStr(SearchDto searchDto, LogicDelDto logicDelDto) {
        if (logicDelDto != null
                && StrUtil.isNotEmpty(logicDelDto.getLogicDelField())
                && StrUtil.isNotEmpty(logicDelDto.getLogicNotDelValue())) {
            String result;
            //搜索条件
            String queryStr = searchDto.getQueryStr();
            //拼凑逻辑删除的条件
            String logicStr = logicDelDto.getLogicDelField() + ":" + logicDelDto.getLogicNotDelValue();
            if (StrUtil.isNotEmpty(queryStr)) {
                result = "(" + queryStr + ") AND " + logicStr;
            } else {
                result = logicStr;
            }
            searchDto.setQueryStr(result);
        }
    }

    /**
     * 访问统计聚合查询
     *
     * @param indexName 索引名
     * @param routing   es的路由
     */
    @Override
    public Map<String, Object> requestStatAgg(String indexName, String routing) throws IOException {
        return aggregationService.requestStatAgg(indexName, routing);
    }
}
