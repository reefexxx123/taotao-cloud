package com.taotao.cloud.uc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表
 *
 * @author dengtao
 * @date 2020/6/15 11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


//    /**
//     * 非数据库字段
//     * 上级部门
//     */
//    @TableField(exist = false)
//    private String parentName;
//
//    /**
//     * 非数据库字段
//     * 等级
//     */
//    @TableField(exist = false)
//    private Integer level;
//
//    /**
//     * 非数据库字段
//     * 子部门
//     */
//    @TableField(exist = false)
//    private List<SysDept> children;

}
