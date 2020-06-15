package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysRoleDept;

import java.util.List;


/**
 * 角色与部门对应关系 服务类
 *
 * @author taotao
 * @date 2020-05-14 14:36:39
 */
public interface ISysRoleDeptService extends IService<SysRoleDept> {

    /**
     * 根据角色id查询部门ids
     * @param roleId
     * @return
     */
    List<SysRoleDept> getRoleDeptIds(int roleId);
}
