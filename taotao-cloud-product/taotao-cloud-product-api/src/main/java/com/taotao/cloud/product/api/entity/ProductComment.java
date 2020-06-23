package com.taotao.cloud.product.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 评价信息
 *
 * @author dengtao
 * @date 2020/4/30 16:06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_comment")
public class ProductComment extends Model<ProductComment> {

    private static final long serialVersionUID = 3522870103927904950L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String productSpecName;

    private Long mallId;

    private Long sceneId;

    private Long customerId;

    private String memberNick;

    private String memberAvatar;

    private String orderCode;

    private short type;

    private short rank;

    private short hasImage;

    private Long commentPicId;

    private short hasSenWord;

    private String originContent;

    private String filterContent;

    private short opType;

    private short replyStatus;

    private String replyContent;

    private String replyOriContent;

    private LocalDateTime replyTime;

    private Long replyUserId;

    private Long replyPicId;

    private short hasAdd;

    private short afterDays;

    private LocalDateTime appendTime;

    private LocalDateTime createTime;

//    @ApiModelProperty(value = "0隐藏  1显示", example = "0")
    private short status;

}
