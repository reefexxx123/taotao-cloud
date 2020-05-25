package com.taotao.cloud.auth.authentication.social.qq.api;

/**
 * 个性化api接口类，定义QQ获取用户信息的api接口
 *
 * @author dengtao
 * @date 2020/4/29 21:01
 */
public interface QQ {

    /**
     * 根据accessToken、appId、openid发请求，获取用户信息
     *
     * @return com.taotao.cloud.auth.authentication.social.qq.api.QQUserInfo
     * @author dengtao
     * @date 2020/4/29 21:01
     */
    QQUserInfo getUserInfo();

}
