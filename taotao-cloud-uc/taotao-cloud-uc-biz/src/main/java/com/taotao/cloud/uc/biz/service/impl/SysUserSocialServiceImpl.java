package com.taotao.cloud.uc.biz.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.uc.api.entity.SysUserSocial;
import com.taotao.cloud.uc.biz.mapper.SysUserSocialMapper;
import com.taotao.cloud.uc.biz.service.ISysUserSocialService;
import org.springframework.stereotype.Service;


/**
 * 社交登录 服务实现类
 *
 * @author dengtao
 * @date 2020/4/30 13:22
 */
@Service
public class SysUserSocialServiceImpl extends ServiceImpl<SysUserSocialMapper, SysUserSocial> implements ISysUserSocialService {

    @Override
    public Boolean selectOne(String id, String type) {
        int count = count(Wrappers.<SysUserSocial>lambdaQuery()
                .and(obj -> obj.eq(SysUserSocial::getProviderUserId, id)
                        .eq(SysUserSocial::getProviderId, type)));

        return count > 0;
    }
}
