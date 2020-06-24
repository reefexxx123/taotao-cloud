package com.taotao.cloud.auth.configuration;

import com.taotao.cloud.auth.serializer.CustomWebResponseExceptionTranslator;
import com.taotao.cloud.auth.service.impl.RedisClientDetailsService;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权服务器 主要是配置客户端信息和认证信息
 *
 * @author dengtao
 * @date 2020/4/29 20:01
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;
    private final RedisRepository redisRepository;
    private final TokenStore tokenStore;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients clients
     * @author dengtao
     * @date 2020/4/29 20:01
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        RedisClientDetailsService clientDetailsServiceImpl = new RedisClientDetailsService(dataSource, redisRepository);
        clientDetailsServiceImpl.setSelectClientDetailsSql(SecurityConstant.DEFAULT_SELECT);
        clientDetailsServiceImpl.setFindClientDetailsSql(SecurityConstant.DEFAULT_FIND);
        clients.withClientDetails(clientDetailsServiceImpl);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services),告诉spring security token的生成方式。
     *
     * @param endpoints endpoints
     * @author dengtao
     * @date 2020/4/29 20:08
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //指定token存储位置
                .tokenStore(tokenStore)
                //指定认证管理器,当你选择了资源所有者密码（password）授权类型的时候，需设置这个属性注入一个 AuthenticationManager 对象。
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        DefaultAccessTokenConverter defaultAccessTokenConverter=new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new CustomUserAuthenticationConverter());

        endpoints.tokenServices(defaultTokenServices());
        endpoints.accessTokenConverter(defaultAccessTokenConverter);
        endpoints.exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，90天
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 90);
        // refresh_token 90天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 90);
        tokenServices.setTokenEnhancer(tokenEnhancer());


        return tokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(4);
            SecurityUser user = (SecurityUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("userId", user.getUserId());
            additionalInfo.put("username", user.getUsername());
            additionalInfo.put("mobile", user.getMobile());
            additionalInfo.put("deptId", user.getDeptId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param oauthServer oauthServer
     * @author dengtao
     * @date 2020/4/29 20:12
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                // 允许客户表单认证,不加的话/oauth/token无法访问
                .allowFormAuthenticationForClients()
                // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()");
    }
}
