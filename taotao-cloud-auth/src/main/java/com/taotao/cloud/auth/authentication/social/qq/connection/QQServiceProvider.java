package com.taotao.cloud.auth.authentication.social.qq.connection;

import com.taotao.cloud.auth.authentication.social.qq.api.QQ;
import com.taotao.cloud.auth.authentication.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 构建QQ登录的 ServiceProvider
 * 由api（AbstractOAuth2ApiBinding实现类） 和 OAuth2Operations（OAuth2Template） 组成
 *
 * @author dengtao
 * @date 2020/4/29 21:06
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {  // 个性化api接口

    /**
     * 获取授权码地址
     */
    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /**
     * 获取用户令牌地址
     */
    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private final String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
