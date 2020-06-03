package com.taotao.cloud.auth.authentication.social;


import com.taotao.cloud.redis.repository.RedisRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;


/**
 * 将第三方用户信息保存到redis里面
 *
 * @author dengtao
 * @date 2020/4/29 20:38
 */
@Component
public class SocialRedisHelper {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * 缓存第三方用户信息
     *
     * @param mkey
     * @param taotaoConnectionData
     * @return void
     * @author dengtao
     * @date 2020/4/29 20:38
     */
    public void saveConnectionData(String mkey, TaotaoConnectionData taotaoConnectionData) {
        redisRepository.set(getKey(mkey), taotaoConnectionData);
    }

    /**
     * 添加mkey和useId
     *
     * @param mkey
     * @param userId
     * @return void
     * @author dengtao
     * @date 2020/4/29 20:38
     */
    public void saveStateUserId(String mkey, String userId) {
        redisRepository.set(getKey(mkey), userId);
    }

    /**
     * 获取缓存的第三方社交账号信息
     *
     * @param mkey
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 20:45
     */
    public String getStateUserId(String mkey) {
        String key = getKey(mkey);
        if (!redisRepository.exists(key)) {
            throw new RuntimeException("无法找到缓存的第三方社交账号信息");
        }
        return (String) redisRepository.get(key);
    }


    /**
     * 第三方社交账号信息进行与业务系统账号绑定
     *
     * @param mkey
     * @param userId
     */
    public void doPostSignUp(String mkey, Integer userId) {
        String key = getKey(mkey);
        if (!redisRepository.exists(key)) {
            throw new RuntimeException("无法找到缓存的第三方社交账号信息");
        }
        TaotaoConnectionData preTaotaoConnectionData = (TaotaoConnectionData) redisRepository.get(key);
        org.springframework.social.connect.ConnectionData connectionData = null;
        if (preTaotaoConnectionData != null) {
            connectionData = new org.springframework.social.connect.ConnectionData(preTaotaoConnectionData.getProviderId(), preTaotaoConnectionData.getProviderUserId(), preTaotaoConnectionData.getDisplayName(), preTaotaoConnectionData.getProfileUrl(), preTaotaoConnectionData.getImageUrl(), preTaotaoConnectionData.getAccessToken(), preTaotaoConnectionData.getSecret(), preTaotaoConnectionData.getRefreshToken(), preTaotaoConnectionData.getExpireTime());
        }
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(preTaotaoConnectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(String.valueOf(userId)).addConnection(connection);
        redisRepository.del(key);
    }

    /**
     * 第三方社交账号信息进行与业务系统账号解绑
     *
     * @param userId
     * @param providerId
     * @return void
     * @author dengtao
     * @date 2020/4/29 20:45
     */
    public void doPostSignDown(Integer userId, String providerId) {
        usersConnectionRepository.createConnectionRepository(String.valueOf(userId)).removeConnections(providerId);
    }

    private String getKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不为空");
        }
        return "taotao:security:social.connect." + key;
    }
}
