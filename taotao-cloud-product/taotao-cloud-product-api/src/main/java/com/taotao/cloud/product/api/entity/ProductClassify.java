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
 * @author dengtao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_classify")
public class ProductClassify extends Model<ProductClassify> {

    private static final long serialVersionUID = 4623225062695180820L;

    @TableId(value = "product_classify_id", type = IdType.AUTO)
    private Integer productClassifyId;

    private String name;

    private short sequence;

    private String path;

    private LocalDateTime updateTime;

    private Integer status;

    private Integer oneLevelCommission;

    private Integer twoLevelCommission;

    private Long companyId;
}
