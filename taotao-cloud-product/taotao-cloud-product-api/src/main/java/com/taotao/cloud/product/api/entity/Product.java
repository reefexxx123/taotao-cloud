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
 * 商品表
 *
 * @author dengtao
 * @date 2020/4/30 16:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = -6173512967678746591L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    private String name;

    private ProductClassify classify;

    private Long supplierId;

    private Long picId;

    private Long videoId;

    private Long detailPicId;

    private Long firstPicId;

    private Long posterPicId;

    private String remark;

    private Short status;

    private Short hasDelete;

    private LocalDateTime createDate;

    private LocalDateTime publishDate;

    private BigDecimal expressFee;

    private Integer refundTime;
}
