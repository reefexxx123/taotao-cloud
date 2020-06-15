package com.taotao.cloud.uc.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.common.enums.DelFlagEnum;
import com.taotao.cloud.common.enums.UserTypeEnum;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.log.utils.LogUtil;
import com.taotao.cloud.order.api.feign.RemoteOrderService;
import com.taotao.cloud.uc.api.dto.RepeatCheckDTO;
import com.taotao.cloud.uc.api.dto.UserDTO;
import com.taotao.cloud.uc.api.entity.SysUser;
import com.taotao.cloud.uc.api.entity.SysUserRole;
import com.taotao.cloud.uc.api.feign.RemoteUserService;
import com.taotao.cloud.uc.api.query.UserQuery;
import com.taotao.cloud.uc.biz.mapper.SysUserMapper;
import com.taotao.cloud.uc.biz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户表 服务实现类
 *
 * @author dengtao
 * @date 2020/4/30 13:22
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysJobService jobService;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private RemoteOrderService remoteOrderService;
    @Autowired
    private RemoteUserService remoteUserService;

    @Override
    public IPage<SysUser> getUsersWithRolePage(UserQuery userQuery) {
//        Result<String> orderInfoResult = remoteOrderService.getOrderInfoById("1234566");
//        log.info(orderInfoResult.getData());
        Page<SysUser> page = new Page<>(userQuery.getCurrent(), userQuery.getSize());
        String traceId = MDC.get("t-traceId");
        LogUtil.info(traceId);
        LogUtil.info("***************");
        LogUtil.debug("naslkdfj;lasdf;lsd;flslfd");
        LogUtil.error("asfljsldfjasldflasdf", null);
        LogUtil.warn("warnwarnwarnwarnwarnwarnwarn");
//        IPage<SysUser> userList = baseMapper.getUserVosPage(page, userQuery, new DataScope());
//        userList.getRecords().forEach(user -> user.setRoleList(roleService.findRolesByUserId(user.getUserId())));
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        baseMapper.insertUser(sysUser);

        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());

        return userRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeUser(Integer userId) {
        baseMapper.updateById(new SysUser().setUserId(userId).setDelFlag(DelFlagEnum.DELETE.getValue()));
        return userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        baseMapper.updateById(sysUser);
        userRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, sysUser.getUserId()));
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());

        return userRoleService.saveBatch(userRoles);
    }

    @Override
    public boolean restPass(Integer userId, String password) {
        return this.updateById(new SysUser().setPassword(password).setUserId(userId));
    }

    @Override
    public SecurityUser findUserInfo(SysUser sysUser) {
        // 权限集合
        Set<String> permissions = findPermsByUserId(sysUser.getUserId());
        // 角色集合
        Set<String> roles = findRoleIdByUserId(sysUser.getUserId());
        SecurityUser securityUser = new SecurityUser(sysUser.getUserId(), sysUser.getUsername(),
                sysUser.getPassword(), permissions, roles);

        BeanUtil.copyProperties(sysUser, securityUser);
        return securityUser;
    }

    @Override
    public SysUser findUserInByName(String username) {
        return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getMobile, SysUser::getEmail, SysUser::getPassword, SysUser::getDeptId, SysUser::getJobId, SysUser::getAvatar)
                .eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return menuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return userRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(SysUserRole::getRoleName)
                .collect(Collectors.toSet());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(UserDTO userDTO) {
        // 查询用户名是否存在
        SysUser byUserInfoName = findUserByUserIdOrUserNameOrMobile(userDTO.getUsername());
        if (ObjectUtil.isNotNull(byUserInfoName)) {
            throw new BaseException("账户名已被注册");
        }
        SysUser securityUser = findUserByUserIdOrUserNameOrMobile(userDTO.getPhone());
        if (ObjectUtil.isNotNull(securityUser)) {
            throw new BaseException("手机号已被注册");
        }
        userDTO.setDeptId(6);
        userDTO.setJobId(4);
        userDTO.setLockFlag("0");
        SysUser sysUser = new SysUser();
        // 对象拷贝
        BeanUtil.copyProperties(userDTO, sysUser);
        // 加密后的密码
//        sysUser.setPassword(UcUtil.encode(userDTO.getPassword()));
        baseMapper.insertUser(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(14);
        sysUserRole.setUserId(sysUser.getUserId());
        return userRoleService.save(sysUserRole);
    }

    @Override
    public boolean updateUserInfo(SysUser sysUser) {
        return baseMapper.updateById(sysUser) > 0;
    }

    @Override
    public SysUser findUserByUserIdOrUserNameOrMobile(String userIdOrUserNameOrMobileOrEmail) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, userIdOrUserNameOrMobileOrEmail)
                .or()
                .eq(SysUser::getMobile, userIdOrUserNameOrMobileOrEmail)
                .or()
                .eq(SysUser::getUserId, userIdOrUserNameOrMobileOrEmail)
                .or()
                .eq(SysUser::getEmail, userIdOrUserNameOrMobileOrEmail)
                .in(SysUser::getType, CollectionUtil.newArrayList(UserTypeEnum.COMPANY.getValue(), UserTypeEnum.BACKEND.getValue()));
        return baseMapper.selectOne(select);
    }

    @Override
    public boolean repeatCheck(RepeatCheckDTO repeatCheckDTO) {
        String fieldVal = repeatCheckDTO.getFieldVal();
        Integer dataId = repeatCheckDTO.getDataId();
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUsername, fieldVal)
                .or()
                .eq(SysUser::getMobile, fieldVal)
                .or()
                .eq(SysUser::getEmail, fieldVal);
        // 不为空说明是编辑情况
        if (ObjectUtil.isNotNull(dataId)) {
            lambdaQueryWrapper.eq(SysUser::getUserId, dataId);
            return baseMapper.selectCount(lambdaQueryWrapper) < 0;
        }
        return baseMapper.selectCount(lambdaQueryWrapper) > 0;
    }

    @Override
    public SysUser getUserBySocial(String providerId, int providerUserId) {
        return baseMapper.getUserBySocial(providerId, providerUserId);
    }

//    @Override
//    public SysUser findSecurityUserByUser(SysUser sysUser) {
//        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
//                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword);
//        if (StrUtil.isNotEmpty(sysUser.getUsername())) {
//            select.eq(SysUser::getUsername, sysUser.getUsername());
//        } else if (StrUtil.isNotEmpty(sysUser.getPhone())) {
//            select.eq(SysUser::getPhone, sysUser.getPhone());
//        } else if (ObjectUtil.isNotNull(sysUser.getUserId()) && sysUser.getUserId() != 0) {
//            select.eq(SysUser::getUserId, sysUser.getUserId());
//        }
//
//
//        return baseMapper.selectOne(select);
//    }

//    @Override
//    public boolean doPostSignUp(SysUser user) {
    // 进行账号校验
//        SysUser sysUser = findSecurityUserByUser(new SysUser().setUsername(user.getUsername()));
//        if (ObjectUtil.isNull(sysUser)) {
//            throw new BaseException("账号不存在");
//        }
//        Integer userId = sysUser.getUserId();
//        try {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername()去验证用户名和密码，
//            // 如果正确，则存储该用户名密码到security 的 context中
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        } catch (Exception e) {
//            if (e instanceof BadCredentialsException) {
//                throw new BaseException("用户名或密码错误", 402);
//            } else if (e instanceof DisabledException) {
//                throw new BaseException("账户被禁用", 402);
//            } else if (e instanceof AccountExpiredException) {
//                throw new BaseException("账户过期无法验证", 402);
//            } else {
//                throw new BaseException("账户被锁定,无法登录", 402);
//            }
//        }
//        //将业务系统的用户与社交用户绑定
//        socialRedisHelper.doPostSignUp(user.getKey(), userId);
//        return true;
//    }

}
