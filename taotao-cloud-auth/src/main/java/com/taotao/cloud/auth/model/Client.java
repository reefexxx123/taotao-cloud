package com.taotao.cloud.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.data.mapper.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Client
 *
 * @author dengtao
 * @date 2020/5/2 11:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
public class Client extends SuperEntity {
    private static final long serialVersionUID = -8185413579135897885L;

    /**
     * 用于唯一标识每一个客户端(client)
     */
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;
    /**
     * 应用名称
     */
    private String clientName;
    /**
     * 资源ID
     */
    private String resourceIds = "";
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 客户端密钥(明文)
     */
    private String clientSecretStr;
    /**
     * 作用域
     */
    private String scope = "all";
    /**
     * 授权方式（A,B,C）
     */
    private String authorizedGrantTypes = "authorization_code,password,refresh_token,client_credentials";
    /**
     * 客户端重定向uri
     */
    private String webServerRedirectUri;
    /**
     * 指定用户的权限范围
     */
    private String authorities = "";
    /**
     * 请求令牌有效时间 设置access_token的有效时间(秒),默认(606012,12小时)
     */
    @TableField(value = "access_token_validity")
    private Integer accessTokenValiditySeconds = 18000;
    /**
     * 刷新令牌有效时间 设置refresh_token有效期(秒)，默认(606024*30, 30填)
     */
    @TableField(value = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds = 28800;
    /**
     * 扩展信息 值必须是json格式
     */
    private String additionalInformation = "{}";
    /**
     * 是否自动放行 默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面，直接跳到redirect_uri
     */
    private String autoapprove = "true";
}
