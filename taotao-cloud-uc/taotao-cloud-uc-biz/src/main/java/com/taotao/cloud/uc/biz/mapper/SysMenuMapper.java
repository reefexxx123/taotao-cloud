package com.taotao.cloud.uc.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.cloud.uc.api.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author dengtao
 * @date 2020/4/30 11:43
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("SELECT m.perms " +
            "FROM sys_menu m, sys_user u, sys_user_role ur, sys_role_menu rm " +
            "WHERE u.user_id = #{userId} and u.user_id = ur.user_id " +
            "      AND ur.role_id = rm.role_id and rm.menu_id = m.menu_id")
    List<String> findPermsByUserId(@Param(value = "userId") Integer userId);

}
