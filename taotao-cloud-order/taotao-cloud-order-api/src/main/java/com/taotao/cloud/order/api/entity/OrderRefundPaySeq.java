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
 * 退款流水
 *
 * @author dengtao
 * @date 2020/4/30 15:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_refund_pay_seq")
public class OrderRefundPaySeq extends Model<OrderRefundPaySeq> {

    private static final Long serialVersionUID = 1L;

    @TableId(value = "order_refund_pay_seq_id", type = IdType.AUTO)
    private Integer orderRefundPaySeqId;

    /**
     * 售后申请ID
     */
    private String refundCode;

    /**
     * 管家审核日期
     */
    private LocalDateTime stewardAuditDate;

    /**
     * 管家id
     */
    private Long stewardId;

    /**
     * 退款金额
     */
    private BigDecimal amount = new BigDecimal(0);

    /**
     * 微信退款ID
     */
    private String wxRefundId;

    /**
     * 微信退款渠道 需要通过微信 “查询退款”接口设置
     */
    private String wxRefundChanel;

    /**
     * 微信退款状态 需要通过微信 “查询退款”接口设置
     */
    private String wxRefundStatus;

    /**
     * 微信退款收款账户 需要通过微信 “查询退款”接口设置
     */
    private String wxRefundTarget;

    /**
     * 退款时间
     */
    private LocalDateTime refundDate;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

}
