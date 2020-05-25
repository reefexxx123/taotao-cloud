package com.taotao.cloud.auth.dto;

import com.taotao.cloud.auth.model.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * ClientDTO
 *
 * @author dengtao
 * @date 2020/5/14 17:05
 */
@Data
@ApiModel(value = "ClientDTO")
@EqualsAndHashCode(callSuper = true)
public class ClientDTO extends Client {
    private static final long serialVersionUID = 1475637288060027265L;

    @ApiModelProperty(value = "权限集合")
    private List<Long> permissionIds;

    @ApiModelProperty(value = "服务集合")
    private Set<Long> serviceIds;
}
