package com.taotao.cloud.product.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_moments")
public class ProductMoments extends Model<ProductMoments> {

    private static final long serialVersionUID = 3522870103927904950L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long productId;

    private String document;

    private Long picId;

    private Integer status;

    private Integer hasVideo;

    private long sendNum = 0;

    private Integer sort = 0;

    private LocalDateTime createDate;

    private LocalDateTime publishTime;

}
