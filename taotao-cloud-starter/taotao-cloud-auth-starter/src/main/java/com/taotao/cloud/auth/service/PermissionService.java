/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.auth.service
 * Descroption:
 * Date: 2020/5/2 20:13
 * Author: dengtao
 */
package com.taotao.cloud.auth.service;

import com.taotao.cloud.auth.model.SecurityMenu;

import java.util.List;

/**
 * 权限服务
 *
 * @author dengtao
 * @date 2020/6/2 15:47
 */
public interface PermissionService {
    /**
     * 查询当前用户拥有的资源权限
     *
     * @param roleCodes 角色code列表，多个以','隔开
     * @return java.util.List<com.taotao.cloud.auth.model.SecurityUserMenu>
     * @author dengtao
     * @date 2020/5/12 20:38
     */
    List<SecurityMenu> findMenuByRoleCodes(String roleCodes);
}
