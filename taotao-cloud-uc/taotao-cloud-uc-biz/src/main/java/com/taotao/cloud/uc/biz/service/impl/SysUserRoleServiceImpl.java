package com.taotao.cloud.uc.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.uc.api.entity.SysUserRole;
import com.taotao.cloud.uc.biz.mapper.SysUserRoleMapper;
import com.taotao.cloud.uc.biz.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色表 服务实现类
 *
 * @author dengtao
 * @date 2020/4/30 11:42
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Override
    public boolean save(SysUserRole entity) {
        return super.save(entity);
    }


    @Override
    public List<SysUserRole> selectUserRoleListByUserId(Integer userId) {
        return baseMapper.selectUserRoleListByUserId(userId);
    }
}
