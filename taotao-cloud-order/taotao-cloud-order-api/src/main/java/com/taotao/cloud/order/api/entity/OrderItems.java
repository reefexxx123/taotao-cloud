package com.taotao.cloud.order.api.entity;


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
 * 订单商品明细
 *
 * @author dengtao
 * @date 2020/4/30 15:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_items")
public class OrderItems extends Model<OrderItems> {

    private static final Long serialVersionUID = 1L;

    @TableId(value = "order_items_id", type = IdType.AUTO)
    private Integer orderItemsId;

    private String itemCode;

    /**
     * 商品SPU ID
     */
    private Long productSpu;

    private String productCode;

    /**
     * 商品SPU名称
     */
    private String productSpuName;

    /**
     * 商品SKU ID
     */
    private Long productSku;

    /**
     * 商品SKU 规格名称
     */
    private String productSkuName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice = BigDecimal.ZERO;

    /**
     * 购买数量
     */
    private Integer buyNum = 0;

    /**
     * 合计金额
     */
    private BigDecimal sumAmount = new BigDecimal(0);

    /**
     * 创建时间
     */
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * 货架号、集采商品商品编码
     */
    private String shelfNum;

    private BigDecimal offerPrice = BigDecimal.ZERO;

    private BigDecimal marketPrice = BigDecimal.ZERO;

    /**
     * 管家分润金额
     */
    private BigDecimal onceProfit = BigDecimal.ZERO;

    /**
     * 管家分润比例
     */
    private BigDecimal onceProfitRatio = BigDecimal.ZERO;

    /**
     * 商品主图
     */
    private String productPicUrl;

    /**
     * 商品图片路径ID
     */
    @ApiModelProperty(value = "商品图片路径ID", required = true)
    private Long productPicId;

    /**
     * 商品图片
     */
    private String productImageJson;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 超时退货期限
     */
    private Integer refundTime;

    /**
     * 退货数量
     */
    private Integer rejectCount = 0;

    /**
     * 商品类型 0 普通商品 1 秒杀商品
     */
    private Integer type = 0;

    @ApiModelProperty("集采退货期（小时）")
    private Integer collectRefundTime;

    @ApiModelProperty("商品条码")
    private String productBarCode;

    // *************配送区域******************

    private Long deliveryOrgId;

    private Long deliveryParentOrgId;

    private String deliveryOrgName;

    private String deliveryParentOrgName;

    @ApiModelProperty(value = "是否展示小区配送", example = "1")
    private Integer hasShowOrderRemark;
}
