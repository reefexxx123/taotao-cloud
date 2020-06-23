package com.taotao.cloud.product.api.entity;


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
 * 商品扩展信息
 *
 * @author dengtao
 * @date 2020/4/30 16:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_ext")
public class ProductExt extends Model<ProductExt> {

    private static final long serialVersionUID = 3522870103927904950L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private int inventory;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private int collectionTimes;

    private int shareTimes;

    private int sellCount;

    private Long version;

    private int sum;

    private int good;

    private int bad;

    private int hasImg;

    private int append;

    private int sensitiveWord;

    private int repeatCustomer;

    private LocalDateTime lastCommentDate;

    private String source;

    private String sourceId;


}
