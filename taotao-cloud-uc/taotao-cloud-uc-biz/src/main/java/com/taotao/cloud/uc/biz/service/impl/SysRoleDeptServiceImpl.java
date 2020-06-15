package com.taotao.cloud.uc.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.uc.api.entity.SysRoleDept;
import com.taotao.cloud.uc.biz.mapper.SysRoleDeptMapper;
import com.taotao.cloud.uc.biz.service.ISysRoleDeptService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 角色与部门对应关系 服务实现类
 *
 * @author dengtao
 * @date 2020/4/30 11:42
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {


    @Override
    public List<SysRoleDept> getRoleDeptIds(int roleId) {
        return baseMapper.selectList(Wrappers.<SysRoleDept>lambdaQuery().select(SysRoleDept::getDeptId).eq(SysRoleDept::getRoleId,roleId));
    }
}
