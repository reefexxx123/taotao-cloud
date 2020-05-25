package com.taotao.cloud.uc.biz.strategy;

import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DataScopeContext
 *
 * @author dengtao
 * @date 2020/4/30 13:26
 */
@Service
public class DataScopeContext {

    @Autowired
    private final Map<String, AbstractDataScopeHandler> strategyMap = new ConcurrentHashMap<>();

    /**
     * Component里边的1是指定其名字，这个会作为key放到strategyMap里
     *
     * @author dengtao
     * @date 2020/4/30 13:26
     */
    public DataScopeContext(Map<String, AbstractDataScopeHandler> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    public List<Integer> getDeptIdsForDataScope(RoleDTO roleDto, Integer type) {
        return strategyMap.get(String.valueOf(type)).getDeptIds(roleDto, DataScopeTypeEnum.valueOf(type));
    }
}
