package com.taotao.cloud.product.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单表
 *
 * @author dengtao
 * @date 2020/4/30 15:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final Long serialVersionUID = 1L;

    @TableId(value = "order_info_id", type = IdType.AUTO)
    private Integer orderInfoId;

    /**
     * 买家ID
     */
    private Long customerId;

    /**
     * 订单金额
     */
    private BigDecimal amount = new BigDecimal(0);

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount = new BigDecimal(0);

    /**
     * 实际支付金额
     */
    private BigDecimal actualAmount = new BigDecimal(0);

    /**
     * 支付时间--支付成功后的时间
     */
    private LocalDateTime payDate;

    /**
     * 订单主状态--OrderConstants中的静态变量
     */
    private Short mainStatus;

    /**
     * 订单子状态--OrderConstants中的静态变量
     */
    private Short childStatus;

    /**
     * 售后主状态
     */
    private Short refundMainStatus = 0;

    /**
     * 售后子状态
     */
    private Short refundChildStatus = 0;

    /**
     * 是否可评价
     * <br/>不可评价 --0
     * <br/>可评价 --1
     * <br/>可追评 --2
     */
    private Short evaluateStatus = 0;

    /**
     * 申请售后的code
     */
    private String refundCode;

    /**
     * 申请售后是否有撤销 1--有撤销 0--没有撤销
     */
    private Short hasCancel = 0;

    /**
     * 发货时间  --- （集采集配  自提点收货时间）
     */
    private LocalDateTime deliveryDate;

    /**
     * 收货时间
     */
    private LocalDateTime receiveDate;

    /**
     * 交易结束时间--每天00：15跑定时修改时间并修改状态
     */
    private LocalDateTime tradeEndTime;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货地址:json的形式存储
     * {"province":"省","city":"市","zone":"区","detail":"详细地址"}
     */
    private String receiverAddressJson;


    /**
     * 冗余收货地址字符串
     */
    private String receiverAddress;

    /**
     * 买家留言
     */
    private String customerMsg;

    /**
     * 取消订单说明
     */
    private String cancelRemark;

    /**
     * 物流公司编码
     */
    private String expressCode;

    /**
     * 物流公司名称
     */
    private String expressName;

    /**
     * 物流单号
     */
    private String postId;

    /**
     * 运营方ID（若是平台型供应商则存储为平台型供应商的ID）
     */
    private Long operatorId;

    /**
     * 运营方名称
     */
    private String operatorName;

    /**
     * 场景方ID（物业公司）
     */
    private Long sceneId;

    /**
     * 场景方名称（物业公司）
     */
    private String sceneName;

    /**
     * 组织ID（所属物业公司(场景方)的子机构或分公司）
     */
    private Long orgId;

    /**
     * 组织名称（所属物业公司(场景方)的子机构或分公司）
     */
    private String orgName;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 一级管家ID
     */
    private Long onceShareStewardId;

    /**
     * 一级管家名称
     */
    private String onceShareStewardName;

    /**
     * 一级管家分润比例
     */
    private BigDecimal onceRatio = new BigDecimal(0);

    /**
     * 一级管家分润金额
     */
    private BigDecimal onceRatioAmount = new BigDecimal(0);


    /**
     * 二级管家ID
     */
    private Long twiceShareStewardId;

    /**
     * 二级管家名称
     */
    private String twiceShareStewardName;

    /**
     * 二级管家分润比例
     */
    private BigDecimal twiceRatio = new BigDecimal(0);

    /**
     * 二级管家分润金额
     */
    private BigDecimal twiceRatioAmount = new BigDecimal(0);


    /**
     * 分润类型  0-管家  1-粉丝  2-普通用户无管家
     */
    private Short businessType;

    /**
     * 商品图片路径ID
     */
    private Long productPicId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * 买家IP
     */
    @ApiModelProperty("买家IP")
    private String createIp;

    /**
     * 订单编码
     */
    private String code;

    /**
     * 逻辑删除订单 0--未删除；1--已删除；
     */
    private Integer hasDelete = 0;

    /**
     * 是否结算 0-未结算，1-已结算
     */
    private Short hasSettlement = 0;

    /**
     * 超时退货期限
     */
    private Integer refundTime;

    /**
     * 订单类型
     */
    private Integer type;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 配送时间
     */
    private String collectDeliveryDate;

    /**
     * 自提配送点id，每日生成的id
     */
    private Long collectProductId;

    /**
     * 优惠券id
     */
    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    private BigDecimal refundAmount;

    private BigDecimal endAmount;


    @ApiModelProperty(value = "秒杀活动id")
    private Long secKillId;


}
