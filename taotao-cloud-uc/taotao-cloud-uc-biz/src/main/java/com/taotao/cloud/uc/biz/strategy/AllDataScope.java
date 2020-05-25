package com.taotao.cloud.uc.biz.strategy;

import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;
import com.taotao.cloud.uc.api.entity.SysDept;
import com.taotao.cloud.uc.biz.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有
 *
 * @author dengtao
 * @date 2020/4/30 13:25
 */
@Component("1")
public class AllDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysDeptService deptService;

    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        List<SysDept> sysDepts = deptService.list();
        return sysDepts.stream().map(SysDept::getDeptId).collect(Collectors.toList());
    }
}
