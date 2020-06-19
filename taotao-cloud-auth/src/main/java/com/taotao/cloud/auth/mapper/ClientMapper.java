package com.taotao.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.auth.model.Client;
import com.taotao.cloud.data.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户端Mapper
 *
 * @author dengtao
 * @date 2020/5/2 11:17
 */
@Repository
public interface ClientMapper extends SuperMapper<Client> {
    @Select("SELECT id, " +
            "       client_id, " +
            "       client_name, " +
            "       resource_ids, " +
            "       client_secret, " +
            "       client_secret_str, " +
            "       web_server_redirect_uri " +
            "FROM   oauth_client_details " +
            "  ${ew.customSqlSegment}")
    List<Client> findList(Page<Client> page, @Param(Constants.WRAPPER) Wrapper<Client> wrapper);
}
