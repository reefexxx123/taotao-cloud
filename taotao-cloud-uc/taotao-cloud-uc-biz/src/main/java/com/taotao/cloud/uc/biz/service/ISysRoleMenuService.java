package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色菜单表 服务类
 *
 * @author taotao
 * @date 2020-05-14 14:36:39
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据用户id查询菜单集合
     * @param userId
     * @return
     */
    List<Integer> getMenuIdByUserId(Integer userId);


    /**
     * 根据角色id查询菜单集合
     * @param roleId
     * @return
     */
    List<Integer> getMenuIdByRoleId(Integer roleId);


}
