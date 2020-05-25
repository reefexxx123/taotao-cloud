package com.taotao.cloud.uc.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.uc.api.entity.SysQqUser;

import java.util.Map;

/**
 * qq用户表
 *
 * @author taotao
 * @date 2020-05-14 14:36:39
 */
public interface ISysQqUserService extends IService<SysQqUser> {
    /**
     * 列表
     *
     * @param params
     * @return
     */
    PageResult<SysQqUser> findList(Map<String, Object> params);
}

