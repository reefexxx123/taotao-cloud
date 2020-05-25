package com.taotao.cloud.uc.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.uc.api.entity.SysGithubUser;
import com.taotao.cloud.uc.api.entity.SysQqUser;
import com.taotao.cloud.uc.biz.mapper.SysGithubUserMapper;
import com.taotao.cloud.uc.biz.service.ISysGithubUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * github用户表
 *
 * @author taotao
 * @date 2020-05-14 14:36:39
 */
@Slf4j
@Service
public class SysGithubUserServiceImpl extends ServiceImpl<SysGithubUserMapper, SysGithubUser> implements ISysGithubUserService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<SysGithubUser> findList(Map<String, Object> params){
        Page<SysGithubUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        LambdaQueryWrapper<SysGithubUser> githubUserLambdaQueryWrapper = Wrappers.<SysGithubUser>lambdaQuery();
        Page<SysGithubUser> pageResult = baseMapper.selectPage(page, githubUserLambdaQueryWrapper);

        PageResult result = PageResult.builder().currentPage(page.getCurrent()).total(pageResult.getTotal())
                .code(ResultEnum.SUCCESS.getCode())
                .pageSize(pageResult.getSize())
                .data(Collections.singletonList(pageResult.getRecords()))
                .build();

        return result;
    }
}
