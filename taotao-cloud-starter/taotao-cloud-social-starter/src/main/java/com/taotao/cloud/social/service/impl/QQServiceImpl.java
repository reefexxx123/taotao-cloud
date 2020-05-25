package com.taotao.cloud.social.service.impl;

import com.alibaba.fastjson.JSON;
import com.taotao.cloud.auth.enums.LoginType;
import com.taotao.cloud.auth.exception.SocialServiceException;
import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.social.config.QqConfig;
import com.taotao.cloud.social.config.SocialRestTemplate;
import com.taotao.cloud.social.entity.QQUserInfo;
import com.taotao.cloud.social.event.QQEvent;
import com.taotao.cloud.social.service.QQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 个性化api接口实现类，实现QQ获取用户信息的逻辑
 * AbstractOAuth2ApiBinding类非单例，每个用户都创建对象存储自己的accessToken走自己的认证流程
 * 所以不放入ioc中
 *
 * @author dengtao
 * @date 2020/4/29 21:13
 */
@Slf4j
public class QQServiceImpl implements QQService {

    /**
     * 获取accessToke的请求地址
     */
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token?code=%s&client_id=%s&redirect_uri=%s&client_secret=%s&grant_type=authorization_code";
    /**
     * 获取openid的请求地址
     */
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * 获取用户信息的请求地址
     */
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    @Autowired
    private QqConfig qqConfig;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public QQUserInfo getUserInfo(String code) {
        try {
            String responseStr = SocialRestTemplate.getRestTemplate().getForObject(String.format(TOKEN_URL, code, qqConfig.getAppId(), qqConfig.getRedirectUri(), qqConfig.getAppSecret(), code), String.class);
            log.info(responseStr);

            if (responseStr != null && responseStr.contains("access_token=")) {
                log.info("获取accessToke的响应：" + responseStr);
                String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
                String accessToken = StringUtils.substringAfterLast(items[0], "=");

                //拼接成最终的openid的请求地址
                String result = SocialRestTemplate.getRestTemplate().getForObject(String.format(URL_GET_OPENID, accessToken), String.class);
                String openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
                log.debug("获取到的openId:{}", openId);

                //拼接成最终的获取用户信息的请求地址
                String object = SocialRestTemplate.getRestTemplate().getForObject(String.format(URL_GET_USERINFO, "", openId), String.class);
                QQUserInfo userInfo = JSON.parseObject(object, QQUserInfo.class);
                userInfo.setOpenId(openId);

                publisher.publishEvent(new QQEvent(userInfo));
                return userInfo;
            }
        } catch (Exception e) {
            log.error("QQ登录信息错误,{}", e.getLocalizedMessage());
        }
        throw new SocialServiceException("QQ登录信息错误", null);

    }

    @Override
    public String getQqAuthUrl() {
        try {
            return "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                    "&client_id=" + qqConfig.getAppId() +
                    "&redirect_uri=" + URLEncoder.encode(qqConfig.getRedirectUri(), "UTF-8") +
                    "&state=" + LoginType.qq.getType() +
                    "&scope=get_user_info";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BaseException("服务异常");
        }
    }
}
