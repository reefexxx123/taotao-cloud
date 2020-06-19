package com.taotao.cloud.elasticsearch.service;

import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.elasticsearch.model.SearchDto;

import java.io.IOException;

/**
 * SearchService
 *
 * @author dengtao
 * @date 2020/5/3 08:01
 */
public interface ISearchService {
    /**
     * StringQuery通用搜索
     *
     * @param indexName 索引名
     * @param searchDto 搜索Dto
     * @return
     */
    PageResult<String> strQuery(String indexName, SearchDto searchDto) throws IOException;
}
