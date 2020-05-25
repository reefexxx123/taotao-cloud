package com.taotao.cloud.uc.biz.strategy;

import com.taotao.cloud.auth.util.SecurityUtil;
import com.taotao.cloud.data.enums.DataScopeTypeEnum;
import com.taotao.cloud.uc.api.dto.RoleDTO;
import com.taotao.cloud.uc.biz.service.ISysDeptService;
import com.taotao.cloud.uc.biz.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 本级以及子级
 *
 * @author dengtao
 * @date 2020/4/30 13:26
 */
@Component("3")
public class ThisLevelChildenDataScope implements AbstractDataScopeHandler {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;


    @Override
    public List<Integer> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
        Integer deptId = userService.findUserInByName(SecurityUtil.getUser().getUsername()).getDeptId();
        return deptService.selectDeptIds(deptId);
    }
}
