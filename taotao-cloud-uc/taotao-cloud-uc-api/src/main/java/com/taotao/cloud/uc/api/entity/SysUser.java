package com.taotao.cloud.uc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.taotao.cloud.common.enums.UserSexTypeEnum;
import com.taotao.cloud.common.enums.UserTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表
 *
 * @author dengtao
 * @date 2020/6/15 11:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实用户名
     */
    private String username;

    /**
     * 用户类型 1前端用户 2商户用户 3后台管理用户
     *
     * @see UserTypeEnum
     */
    private Integer type;

    /**
     * 性别 1男 2女 0未知
     *
     * @see UserSexTypeEnum
     */
    private Integer sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 岗位ID
     */
    private Integer jobId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 1-正常，2-锁定
     */
    private int lockFlag;

    /**
     * 1-正常，2-删除
     */
    private int delFlag;

//    /**
//     * 非数据库字段
//     * 角色列表
//     */
//    @TableField(exist = false)
//    private List<SysRole> roleList;
//
//    /**
//     * 非数据库字段
//     * 部门名称
//     */
//    @TableField(exist = false)
//    private String deptName;
//
//    /**
//     * 非数据库字段
//     * 岗位名称
//     */
//    @TableField(exist = false)
//    private String jobName;

}
