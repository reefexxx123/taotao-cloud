package com.taotao.cloud.uc.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.uc.api.dto.DeptDTO;
import com.taotao.cloud.uc.api.entity.SysDept;
import com.taotao.cloud.uc.api.vo.SysDeptTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * 部门管理 服务类
 *
 * @author dengtao
 * @date 2020/4/30 11:10
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 保存部门信息
     *
     * @param entity
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:10
     */
    @Override
    boolean save(SysDept entity);

    /**
     * 查询部门信息
     *
     * @param
     * @return java.util.List<com.taotao.cloud.uc.api.entity.SysDept>
     * @author dengtao
     * @date 2020/4/30 11:10
     */
    List<SysDept> selectDeptList();

    /**
     * 更新部门
     *
     * @param entity
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:10
     */
    boolean updateDeptById(DeptDTO entity);

    /**
     * 删除部门
     *
     * @param id
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:10
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 批量删除部门
     *
     * @param ids
     * @return boolean
     * @author dengtao
     * @date 2020/4/30 11:11
     */
    boolean batchDeleteDeptByIds(List<Integer> ids);

    /**
     * 根据部门id查询部门名称
     *
     * @param deptId
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/30 11:11
     */
    String selectDeptNameByDeptId(int deptId);

    /**
     * 根据部门名称查询
     *
     * @param deptName
     * @return java.util.List<com.taotao.cloud.uc.api.entity.SysDept>
     * @author dengtao
     * @date 2020/4/30 11:11
     */
    List<SysDept> selectDeptListBydeptName(String deptName);

    /**
     * 通过此部门id查询于此相关的部门ids
     *
     * @param deptId
     * @return java.util.List<java.lang.Integer>
     * @author dengtao
     * @date 2020/4/30 11:11
     */
    List<Integer> selectDeptIds(int deptId);

    /**
     * 查询部门信息 部门树
     *
     * @param
     * @return java.util.List<com.taotao.cloud.uc.api.vo.SysDeptTreeVo>
     * @author dengtao
     * @date 2020/4/30 11:11
     */
    List<SysDeptTreeVo> queryDepartTreeList();


}
