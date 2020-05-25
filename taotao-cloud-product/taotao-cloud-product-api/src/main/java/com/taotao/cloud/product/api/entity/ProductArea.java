package com.taotao.cloud.product.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品销售范围
 *
 * @author dengtao
 * @date 2020/4/30 16:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_area")
public class ProductArea extends Model<ProductArea> {

    private static final long serialVersionUID = -3401835090916645698L;

    @TableId(value = "product_area_id", type = IdType.AUTO)
    private Integer productAreaId;


    private String regionJson;

    private int type;

}
