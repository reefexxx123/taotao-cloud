package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysUserRole;

import java.util.List;


/**
 * 用户角色表 服务类
 *
 * @author taotao
 * @date 2020-05-14 14:36:39
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    /**
     * 根据用户id查询用户角色关系
     *
     * @param userId
     * @return
     */
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}
