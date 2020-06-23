package com.taotao.cloud.shardingjdbc;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;

/**
 * 数据库分源算法
 *
 * @author dengtao
 * @date 2020/6/22 17:30
 */
public class DataSourceShardingAlgorithm implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        return hintShardingValue.getValues();
    }
}
