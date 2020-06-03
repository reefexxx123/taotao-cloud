package com.taotao.cloud.elasticsearch.annotation;

import com.taotao.cloud.elasticsearch.service.impl.AggregationServiceImpl;
import com.taotao.cloud.elasticsearch.service.impl.IndexServiceImpl;
import com.taotao.cloud.elasticsearch.service.impl.QueryServiceImpl;
import com.taotao.cloud.elasticsearch.service.impl.SearchServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制是否加载搜索中心客户端的Service
 *
 * @author dengtao
 * @date 2020/5/3 07:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({QueryServiceImpl.class, SearchServiceImpl.class, AggregationServiceImpl.class, IndexServiceImpl.class})
public @interface EnableTaoTaoSearchClient {

}
