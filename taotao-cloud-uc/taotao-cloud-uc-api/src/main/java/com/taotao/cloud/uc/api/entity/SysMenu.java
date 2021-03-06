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
 * 菜单表
 *
 * @author dengtao
 * @date 2020/6/15 11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限标识
     */
    private String perms;

    /**
     * 前端path / 即跳转路由
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否缓存页面: 0:不是  1:是（默认值1）
     */
    private Boolean keepAlive;

    /**
     * 是否隐藏路由菜单: 0否,1是（默认值0）
     */
    private Boolean hidden;

    /**
     * 聚合路由
     */
    private Boolean alwaysShow;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * 是否为外链
     */
    private Boolean isFrame;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型 （类型   0：目录   1：菜单   2：按钮）
     */
    private Integer type;

    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


//    /**
//     * 非数据库字段
//     * 父菜单名称
//     */
//    @TableField(exist = false)
//    private String parentName;
//
//    /**
//     * 非数据库字段
//     * 菜单等级
//     */
//    @TableField(exist = false)
//    private Integer level;
//
//    /**
//     * 非数据库字段
//     * 子菜单
//     */
//    @TableField(exist = false)
//    private List<SysMenu> children;
//
//    @TableField(exist = false)
//    private Integer key;

}
