package com.taotao.cloud.order.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单超时发货基本信息
 *
 * @author dengtao
 * @date 2020/4/30 15:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_overtime")
public class OrderOvertime extends Model<OrderOvertime> {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 支付时间--支付成功后的时间
     */
    private LocalDateTime payDate;

    /**
     * 场景方ID（物业公司）
     */
    private Long sceneId;

    /**
     * 场景方名称（物业公司）
     */
    private String sceneName;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 超时的类型
     */
    private String overTimeType;

    /**
     * 超时时间
     */
    private LocalDateTime overTime;

    /**
     * 创建时间
     */
    private LocalDateTime createDate = LocalDateTime.now();


}
