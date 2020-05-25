package com.taotao.cloud.uc.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysUserSocial;

/**
 * <p>
 * 社交登录 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-27
 */
public interface ISysUserSocialService extends IService<SysUserSocial> {

    /**
     * 查询是否存在第三方用户
     *
     * @param id   第三方用户id
     * @param type 第三方类型
     * @return com.taotao.cloud.uc.api.entity.SysUserSocial
     * @author dengtao
     * @date 2020/5/22 13:17
     */
    Boolean selectOne(String id, String type);
}
