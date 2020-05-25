package com.taotao.cloud.uc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * github 用户信息
 *
 * @author dengtao
 * @date 2020/4/29 20:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_github_user")
public class SysGithubUser implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private long id;

    private String name;
    private String username;
    private String location;
    private String company;
    private String blog;
    private String email;
    private String createdDate;
    private String profileImageUrl;
    private String avatarUrl;
}
