package com.taotao.cloud.product.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author dengtao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_spec_detail")
public class ProductSpecDetail extends Model<ProductSpecDetail> {

    private static final long serialVersionUID = 3522870103927904950L;

    @TableId(value = "product_spec_detail_id", type = IdType.AUTO)
    private Integer productSpecDetailId;

    private Product product;

    private String shelfNum;

    private String name;

    private String attributeJson;

    private int inventory;

    private BigDecimal offerPrice;

    private BigDecimal costPrice;

    private BigDecimal minSellPrice;

    private BigDecimal maxSellPrice;

    private String remark;

    private int sellCount;

    private String sourceId;
}
