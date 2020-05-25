package com.taotao.cloud.order.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单支付流水
 *
 * @author dengtao
 * @date 2020/4/30 15:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_pay_seq")
public class OrderPaySeq extends Model<OrderPaySeq> {

    private static final Long serialVersionUID = 1L;

    @TableId(value = "order_pay_seq_id", type = IdType.AUTO)
    private Integer orderPaySeqId;


    /**
     * 支付流水编码--需要与微信的预支付ID进行关联
     */
    private String payCode;

    /**
     * 买家ID
     */
    private Long customerId;

    /**
     * 付款方银行编码
     */
    private String payerBankCode;

    /**
     * 交易金额
     */
    private BigDecimal actualAmount = new BigDecimal(0);

    /**
     * 微信预支付ID
     */
    private String prepayId;

    /**
     * 微信交易ID
     */
    private String transactionId;

    /**
     * 微信商户ID
     */
    private String mchId;

    /**
     * 微信APPID
     */
    private String appId;

    /**
     * 状态 0-等待支付 1-超时关闭 2-支付失败 3-支付成功
     */
    private Short status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createDate = LocalDateTime.now();

}
