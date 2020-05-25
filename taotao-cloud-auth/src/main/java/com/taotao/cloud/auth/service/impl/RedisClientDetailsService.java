package com.taotao.cloud.auth.service.impl;

import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.redis.template.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;


/**
 * 将oauth_client_details表数据缓存到redis，这里做个缓存优化
 * 后台管理模块中有对oauth_client_details的crud， 注意同步redis的数据
 * 注意对oauth_client_details清除redis db部分数据的清空
 *
 * @author dengtao
 * @date 2020/4/29 17:40
 */
@Slf4j
@Service
public class RedisClientDetailsService extends JdbcClientDetailsService {

    private final RedisRepository redisRepository;

    public RedisClientDetailsService(@Qualifier("dataSource") DataSource dataSource,
                                     RedisRepository redisRepository) {
        super(dataSource);
        this.redisRepository = redisRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        ClientDetails clientDetails = (ClientDetails) redisRepository.get(clientRedisKey(clientId));
        if (clientDetails == null) {
            clientDetails = cacheAndGetClient(clientId);
        }
        return clientDetails;
    }

    /**
     * 缓存client并返回client
     *
     * @param clientId clientId
     * @return org.springframework.security.oauth2.provider.ClientDetails
     * @author dengtao
     * @date 2020/4/29 17:42
     */
    private ClientDetails cacheAndGetClient(String clientId) {
        ClientDetails clientDetails = null;
        try {
            clientDetails = super.loadClientByClientId(clientId);
            if (null != clientDetails) {
                redisRepository.setExpire(clientRedisKey(clientId), clientDetails, 30*24*24*60);
                log.info("缓存clientId:{},{}", clientId, clientDetails);
            }
        } catch (NoSuchClientException e) {
            log.error("未查询到clientId:{}", clientId);
        } catch (InvalidClientException e) {
            log.error("cacheAndGetClient-invalidClient:{}", clientId, e);
        }
        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }

    @Override
    public void removeClientDetails(String clientId) {
        super.removeClientDetails(clientId);
        removeRedisCache(clientId);
    }

    /**
     * 删除redis缓存
     *
     * @param clientId clientId
     * @return void
     * @author dengtao
     * @date 2020/4/29 17:44
     */
    private void removeRedisCache(String clientId) {
        redisRepository.del(clientRedisKey(clientId));
    }

    /**
     * 将oauth_client_details全表刷入redis
     *
     * @return void
     * @author dengtao
     * @date 2020/4/29 17:45
     */
    public void loadAllClientToCache() {
        List<ClientDetails> list = super.listClientDetails();
        if (CollectionUtils.isEmpty(list)) {
            log.error("oauth_client_details表数据为空，请检查");
            return;
        }

        list.parallelStream().forEach(client -> redisRepository.set(clientRedisKey(client.getClientId()), client));
    }

    private String clientRedisKey(String clientId) {
        return SecurityConstant.CACHE_CLIENT_KEY + ":" + clientId;
    }
}
