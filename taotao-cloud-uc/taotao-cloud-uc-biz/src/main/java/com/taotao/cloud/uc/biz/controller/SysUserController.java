package com.taotao.cloud.uc.biz.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.auth.util.SecurityUtil;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.SysOperateLog;
import com.taotao.cloud.uc.api.dto.UserDTO;
import com.taotao.cloud.uc.api.entity.SysUser;
import com.taotao.cloud.uc.biz.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * 用户管理API
 *
 * @author dengtao
 * @date 2020/4/30 13:12
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理API", tags = {"用户管理API"})
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @ApiOperation("保存用户包括角色和部门")
    @SysOperateLog(descrption = "保存用户包括角色和部门")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Result<Boolean> add(@RequestBody UserDTO userDto) {
        return Result.succeed(userService.insertUser(userDto));
    }

    @ApiOperation("查询用户集合")
    @SysOperateLog(descrption = "查询用户集合")
    @PreAuthorize("hasAuthority('sys:user:view')")
    @GetMapping
    public Result<PageResult<SysUser>> getList(Page page, UserDTO userDTO) {
        IPage<SysUser> pageResult = userService.getUsersWithRolePage(page, userDTO);

        PageResult result = PageResult.builder().currentPage(page.getCurrent()).total(pageResult.getTotal())
                .code(ResultEnum.SUCCESS.getCode())
                .pageSize(pageResult.getSize())
                .data(Collections.singletonList(pageResult.getRecords()))
                .build();
        return Result.succeed(result);
    }


    @ApiOperation("更新用户包括角色和部门")
    @SysOperateLog(descrption = "更新用户包括角色和部门")
    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping
    public Result<Boolean> update(@RequestBody UserDTO userDto) {
        return Result.succeed(userService.updateUser(userDto));
    }

    @ApiOperation("根据用户id删除用户包括角色和部门")
    @SysOperateLog(descrption = "根据用户id删除用户包括角色和部门")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{userId}")
    public Result<Boolean> delete(@PathVariable("userId") Integer userId) {
        return Result.succeed(userService.removeUser(userId));
    }

    @ApiOperation("重置密码")
    @SysOperateLog(descrption = "重置密码")
    @PreAuthorize("hasAuthority('sys:user:rest:password')")
    @PutMapping("/rest/password")
    public Result<Boolean> restPass(@RequestBody UserDTO userDTO) {
        return Result.succeed(userService.restPass(userDTO.getUserId(), userDTO.getPassword()));
    }

    @ApiOperation("获取个人信息")
    @GetMapping("/info")
    public Result<SysUser> getUserInfo() {
        return Result.succeed(userService.findUserInByName(SecurityUtil.getUser().getUsername()));
    }

    @ApiOperation("获取用户信息")
    @SysOperateLog(descrption = "获取用户信息")
    @GetMapping("/info/{userIdOrUserNameOrMobileOrEmail}")
    public Result<SecurityUser> getInfo(@PathVariable String userIdOrUserNameOrMobileOrEmail) {
        SysUser user = userService.findUserByUserIdOrUserNameOrMobile(userIdOrUserNameOrMobileOrEmail);
        if (user == null) {
            throw new BusinessException("未查询到用户数据");
        }
        return Result.succeed(userService.findUserInfo(user));
    }

    @ApiOperation("第三方登录调用获取用户信息")
    @SysOperateLog(descrption = "第三方登录调用获取用户信息")
    @GetMapping("/info/social")
    public Result<SecurityUser> getUserInfoBySocial(@RequestParam(value = "providerId") String providerId,
                                                    @RequestParam(value = "providerUserId") int providerUserId) {
        SysUser sysUser = userService.getUserBySocial(providerId, providerUserId);
        SecurityUser securityUser = new SecurityUser(sysUser.getUserId(), sysUser.getUsername(),
                sysUser.getPassword(), CollectionUtil.newHashSet(), CollectionUtil.newHashSet());

        BeanUtil.copyProperties(sysUser, securityUser);
        return Result.succeed(securityUser);
    }

    @ApiOperation("修改密码")
    @SysOperateLog(descrption = "修改密码")
    @PreAuthorize("hasAuthority('sys:user:update:password')")
    @PutMapping("/update/password")
    public Result<Boolean> updatePass(@RequestBody SysUser sysUser) {
        // 校验密码流程
        SysUser user = userService.findUserByUserIdOrUserNameOrMobile(sysUser.getUsername());
        if (!SecurityUtil.validatePass(sysUser.getPassword(), user.getPassword())) {
            throw new BaseException("原密码错误");
        }
        // 修改密码流程
        SysUser userForPass = new SysUser();
        userForPass.setUserId(user.getUserId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userForPass.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return Result.succeed(userService.updateUserInfo(userForPass));
    }

}

