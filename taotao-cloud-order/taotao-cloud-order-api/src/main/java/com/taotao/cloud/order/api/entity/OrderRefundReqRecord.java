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
 * 申请售后操作记录
 *
 * @author dengtao
 * @date 2020/4/30 15:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_refund_req_record")
public class OrderRefundReqRecord extends Model<OrderRefundReqRecord> {

    private static final Long serialVersionUID = 1L;


    @TableId(value = "order_refund_req_record_id", type = IdType.AUTO)
    private Integer orderRefundReqRecordId;

    private String orderCode;

    private String itemCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人名称
     */
    private String createName;

    /**
     * 操作人名称
     */
    private Long createId;

    /**
     * 操作人昵称
     */
    private String createNick;

    /**
     * 扩展信息
     */
    private String ext;

    private Short reqRecordType;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

}
