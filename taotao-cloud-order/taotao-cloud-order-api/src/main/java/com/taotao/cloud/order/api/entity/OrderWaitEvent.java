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
 * 订单的定时任务处理表
 *
 * @author dengtao
 * @date 2020/4/30 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_wait_event")
public class OrderWaitEvent extends Model<OrderWaitEvent> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 事件类型
     */
    private Short eventType;

    /**
     * 事件状态；1--已处理；0--待处理
     */
    private Short eventStatus = 0;

    /**
     * 触发时间
     */
    private LocalDateTime triggerTime;

    /**
     * 事件处理结果
     */
    private String eventResult;

    private String refundCode;

    /**
     * 创建时间
     */
    private LocalDateTime createDate = LocalDateTime.now();

}
