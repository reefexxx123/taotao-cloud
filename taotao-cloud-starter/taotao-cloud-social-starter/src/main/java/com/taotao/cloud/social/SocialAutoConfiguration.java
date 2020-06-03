/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.social
 * Date: 2020/5/7 09:08
 * Author: dengtao
 */
package com.taotao.cloud.social;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.social.properties.GitHubProperties;
import com.taotao.cloud.social.properties.GiteeProperties;
import com.taotao.cloud.social.properties.QqProperties;
import com.taotao.cloud.social.listener.SocialListener;
import com.taotao.cloud.social.service.GitHubService;
import com.taotao.cloud.social.service.GiteeService;
import com.taotao.cloud.social.service.SocialService;
import com.taotao.cloud.social.service.impl.GitHubServiceImpl;
import com.taotao.cloud.social.service.impl.GiteeServiceImpl;
import com.taotao.cloud.social.service.impl.QQServiceImpl;
import com.taotao.cloud.social.service.QQService;
import com.taotao.cloud.social.service.impl.SocialServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 */
@EnableConfigurationProperties({GiteeProperties.class, GitHubProperties.class, QqProperties.class})
public class SocialAutoConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtil.info(SocialAutoConfiguration.class, StarterNameConstant.TAOTAO_CLOUD_SOCIAL_STARTER, "第三方认证模块已启动");
    }

    @Bean
    public SocialListener socialListener(){
        return new SocialListener();
    }

    @Bean
    public SocialService socialService(){
        return new SocialServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(prefix = "taotao.cloud.social.security.gitee", name = "app-id")
    public GiteeService giteeService() {
        return new GiteeServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(prefix = "taotao.cloud.social.security.github", name = "app-id")
    public GitHubService gitHubService() {
        return new GitHubServiceImpl();
    }

    @Bean
    @ConditionalOnProperty(prefix = "taotao.cloud.social.security.qq", name = "app-id")
    public QQService qqService() {
        return new QQServiceImpl();
    }

}
