package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysLog;

import java.io.Serializable;

/**
 * 系统日志 服务类
 *
 * @author dengtao
 * @date 2020/4/30 11:36
 */
public interface ISysLogService extends IService<SysLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:36
     */
    @Override
    boolean save(SysLog entity);

    /**
     * 分页查询日志
     *
     * @param page
     * @param pageSize
     * @param type
     * @param userName
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.taotao.cloud.uc.api.entity.SysLog>
     * @author dengtao
     * @date 2020/4/30 11:36
     */
    IPage<SysLog> selectLogList(Integer page, Integer pageSize, Integer type, String userName);

    /**
     * 根据id删除日志
     *
     * @param id
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:36
     */
    @Override
    boolean removeById(Serializable id);


}
