package com.taotao.cloud.uc.biz.strategy;


import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;

import java.util.List;


/**
 * 创建抽象策略角色 主要作用 数据权限范围使用
 *
 * @author dengtao
 * @date 2020/4/30 13:25
 */
public interface AbstractDataScopeHandler {

    /**
     * 获取部门ids
     *
     * @param roleDto
     * @param dataScopeTypeEnum
     * @return java.util.List<java.lang.Integer>
     * @author dengtao
     * @date 2020/4/30 13:25
     */
    List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum);
}
