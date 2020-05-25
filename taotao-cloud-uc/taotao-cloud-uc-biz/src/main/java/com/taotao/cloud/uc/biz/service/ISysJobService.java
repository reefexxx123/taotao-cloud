package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.entity.SysJob;

import java.io.Serializable;
import java.util.List;

/**
 * 岗位管理 服务类
 *
 * @author dengtao
 * @date 2020/4/30 11:32
 */
public interface ISysJobService extends IService<SysJob> {

    /**
     * 保存岗位
     *
     * @param entity
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:32
     */
    @Override
    boolean save(SysJob entity);

    /**
     * 根据id删除岗位
     *
     * @param id
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:32
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据id更新岗位
     *
     * @param entity
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:33
     */
    @Override
    boolean updateById(SysJob entity);

    /**
     * 分页查询岗位列表
     *
     * @param page
     * @param pageSize
     * @param jobName
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.taotao.cloud.uc.api.entity.SysJob>
     * @author dengtao
     * @date 2020/4/30 11:33
     */
    IPage<SysJob> selectJobList(int page, int pageSize, String jobName);


    /**
     * 根据部门id查询所属下的岗位信息
     *
     * @param deptId
     * @return java.util.List<com.taotao.cloud.uc.api.entity.SysJob>
     * @author dengtao
     * @date 2020/4/30 11:33
     */
    List<SysJob> selectJobListByDeptId(Integer deptId);

    /**
     * 功能描述
     *
     * @param jobId
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/30 11:33
     */
    String selectJobNameByJobId(Integer jobId);

    /**
     * 批量删除岗位
     *
     * @param ids
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:33
     */
    boolean batchDeleteJobByIds(List<Integer> ids);

}
