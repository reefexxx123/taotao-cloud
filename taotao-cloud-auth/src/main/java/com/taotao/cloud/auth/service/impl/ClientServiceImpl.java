package com.taotao.cloud.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.auth.mapper.ClientMapper;
import com.taotao.cloud.auth.model.Client;
import com.taotao.cloud.auth.service.IClientService;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.lock.DistributedLock;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.service.impl.SuperServiceImpl;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * ClientServiceImpl
 *
 * @author dengtao
 * @date 2020/4/29 15:22
 */
@Slf4j
@Service
public class ClientServiceImpl extends SuperServiceImpl<ClientMapper, Client> implements IClientService {

    private final static String LOCK_KEY_CLIENT_ID = CommonConstant.LOCK_KEY_PREFIX + "clientId:";

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DistributedLock lock;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> saveClient(Client client) {
        client.setClientSecret(passwordEncoder.encode(client.getClientSecretStr()));
        String clientId = client.getClientId();
        super.saveOrUpdateIdempotency(client, lock
                , LOCK_KEY_CLIENT_ID + clientId
                , new QueryWrapper<Client>().eq("client_id", clientId)
                , clientId + "已存在");
        return Result.succeed(true);
    }

    @Override
    public Result<PageResult<Client>> listClient(Map<String, Object> params, boolean isPage) {
        Page<Client> page;
        if (isPage) {
            page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
        } else {
            page = new Page<>(1, -1);
        }
        List<Client> list = baseMapper.findList(page, params);
        page.setRecords(list);
        PageResult<Client> pageResult = PageResult.<Client>builder().data(list).code(ResultEnum.SUCCESS.getCode()).total(page.getTotal())
                .currentPage(page.getCurrent()).pageSize(page.getSize()).build();
        return Result.succeed(pageResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> delClient(long id) {
        String clientId = baseMapper.selectById(id).getClientId();
        baseMapper.deleteById(id);
        redisRepository.del(clientRedisKey(clientId));
        return Result.succeed(true);
    }

    private String clientRedisKey(String clientId) {
        return SecurityConstant.CACHE_CLIENT_KEY + ":" + clientId;
    }
}
