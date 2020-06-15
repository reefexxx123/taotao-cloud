package com.taotao.cloud.uc.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.uc.api.entity.SysRoleMenu;
import com.taotao.cloud.uc.biz.mapper.SysRoleMenuMapper;
import com.taotao.cloud.uc.biz.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 角色菜单表 服务实现类
 *
 * @author dengtao
 * @date 2020/4/30 11:42
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Override
    public List<Integer> getMenuIdByUserId(Integer userId) {
        return baseMapper.getMenuIdByUserId(userId);
    }

    @Override
    public List<Integer> getMenuIdByRoleId(Integer roleId) {
        List<SysRoleMenu> sysRoleMenus = baseMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().select(SysRoleMenu::getMenuId).eq(SysRoleMenu::getRoleId, roleId));
        return sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }
}
