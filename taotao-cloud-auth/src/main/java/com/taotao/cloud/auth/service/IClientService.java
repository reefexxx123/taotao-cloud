package com.taotao.cloud.auth.service;


import com.taotao.cloud.auth.model.Client;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.service.ISuperService;

import java.util.Map;

/**
 * IClientService
 *
 * @author dengtao
 * @date 2020/4/29 15:13
 */
public interface IClientService extends ISuperService<Client> {
    /**
     * 添加应用
     *
     * @param clientDto clientDto
     * @return com.taotao.cloud.common.model.Result
     * @author dengtao
     * @date 2020/4/29 15:14
     */
    Result<Boolean> saveClient(Client clientDto);

    /**
     * 查询应用列表
     *
     * @param params
     * @param isPage 是否分页
     * @return com.taotao.cloud.common.model.PageResult<com.taotao.cloud.auth.model.Client>
     * @author dengtao
     * @date 2020/4/29 15:23
     */
    Result<PageResult<Client>> listClient(Map<String, Object> params, boolean isPage);

    /**
     * 删除应用
     *
     * @param id id
     * @return com.taotao.cloud.common.model.Result<java.lang.String>
     * @author dengtao
     * @date 2020/4/29 15:24
     */
    Result<Boolean> delClient(long id);
}
