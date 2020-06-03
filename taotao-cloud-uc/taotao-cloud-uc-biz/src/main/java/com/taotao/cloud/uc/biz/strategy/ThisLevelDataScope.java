package com.taotao.cloud.uc.biz.strategy;

import com.taotao.cloud.auth.utils.SecurityUtil;
import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;
import com.taotao.cloud.uc.biz.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 本级
 *
 * @author dengtao
 * @date 2020/4/30 13:27
 */
@Component("2")
public class ThisLevelDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysUserService userService;

    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        // 用于存储部门id
        List<Integer> deptIds = new ArrayList<>();
        deptIds.add(userService.findUserInByName(SecurityUtil.getUser().getUsername()).getDeptId());
        return deptIds;
    }
}
