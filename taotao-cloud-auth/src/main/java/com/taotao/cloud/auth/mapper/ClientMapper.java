package com.taotao.cloud.auth.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.auth.model.Client;
import com.taotao.cloud.data.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author dengtao
 * @date 2020/5/2 11:17
*/
@Mapper
public interface ClientMapper extends SuperMapper<Client> {
    List<Client> findList(Page<Client> page, @Param("params") Map<String, Object> params );
}
