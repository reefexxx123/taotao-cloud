package com.taotao.cloud.uc.biz.strategy;

import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;
import com.taotao.cloud.uc.biz.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义 数据隔离
 *
 * @author dengtao
 * @date 2020/4/30 13:26
 */
@Component("4")
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysDeptService deptService;

    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        List<Integer> roleDeptIds = roleDto.getRoleDepts();
        List<Integer> ids = new ArrayList<>();
        for (Integer deptId : roleDeptIds) {
            ids.addAll(deptService.selectDeptIds(deptId));
        }
        Set<Integer> set = new HashSet<>(ids);
        ids.clear();
        ids.addAll(set);
        return ids;
    }
}
