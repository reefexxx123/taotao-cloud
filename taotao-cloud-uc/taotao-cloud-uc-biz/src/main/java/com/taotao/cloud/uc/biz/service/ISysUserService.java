package com.taotao.cloud.uc.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.uc.api.dto.RepeatCheckDTO;
import com.taotao.cloud.uc.api.dto.UserDTO;
import com.taotao.cloud.uc.api.entity.SysUser;
import com.taotao.cloud.uc.api.query.UserQuery;

import java.util.Set;

/**
 * 用户表 服务类
 *
 * @author dengtao
 * @date 2020/4/30 13:20
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param userQuery
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.taotao.cloud.uc.api.entity.SysUser>
     * @author dengtao
     * @date 2020/4/30 13:20
     */
    IPage<SysUser> getUsersWithRolePage(UserQuery userQuery);

    /**
     * 保存用户以及角色部门等信息
     *
     * @param userDto
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:20
     */
    boolean insertUser(UserDTO userDto);

    /**
     * 更新用户以及角色部门等信息
     *
     * @param userDto
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:20
     */
    boolean updateUser(UserDTO userDto);

    /**
     * 删除用户信息
     *
     * @param userId
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:20
     */
    boolean removeUser(Integer userId);

    /**
     * 重置密码
     *
     * @param userId
     * @param password
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:20
     */
    boolean restPass(Integer userId, String password);

    /**
     * 获取授权用户信息
     *
     * @param sysUser
     * @return com.taotao.cloud.auth.model.SecurityUser
     * @author dengtao
     * @date 2020/4/30 13:21
     */
    SecurityUser findUserInfo(SysUser sysUser);

    /**
     * 通过用户名查找用户个人信息
     *
     * @param username
     * @return com.taotao.cloud.uc.api.entity.SysUser
     * @author dengtao
     * @date 2020/4/30 13:21
     */
    SysUser findUserInByName(String username);

    /**
     * 根据用户id查询权限
     *
     * @param userId
     * @return java.util.Set<java.lang.String>
     * @author dengtao
     * @date 2020/4/30 13:21
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 通过用户id查询角色集合
     *
     * @param userId
     * @return java.util.Set<java.lang.String>
     * @author dengtao
     * @date 2020/4/30 13:21
     */
    Set<String> findRoleIdByUserId(Integer userId);

    /**
     * 注册用户
     *
     * @param userDTO
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:21
     */
    boolean register(UserDTO userDTO);

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:22
     */
    boolean updateUserInfo(SysUser sysUser);

    /**
     * 根据用户id / 用户名 / 手机号查询用户
     *
     * @param userIdOrUserNameOrMobileOrEmail
     * @return com.taotao.cloud.uc.api.entity.SysUser
     * @author dengtao
     * @date 2020/4/30 13:22
     */
    SysUser findUserByUserIdOrUserNameOrMobile(String userIdOrUserNameOrMobileOrEmail);

    /**
     * 数据校验
     *
     * @param repeatCheckDTO
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 13:22
     */
    boolean repeatCheck(RepeatCheckDTO repeatCheckDTO);

    /**
     * 根据社交类型和社交id查询社交用户信息
     *
     * @param providerId
     * @param providerUserId
     * @return com.taotao.cloud.uc.api.entity.SysUser
     * @author dengtao
     * @date 2020/4/30 13:22
     */
    SysUser getUserBySocial(String providerId, int providerUserId);


}
