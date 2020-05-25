/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.social
 * Date: 2020/5/7 09:08
 * Author: dengtao
 */
package com.taotao.cloud.social;

import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.utils.LogUtils;
import com.taotao.cloud.social.config.GitHubConfig;
import com.taotao.cloud.social.config.GiteeConfig;
import com.taotao.cloud.social.config.QqConfig;
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
 * @create 2020/5/7 09:08
 */
@EnableConfigurationProperties({GiteeConfig.class, GitHubConfig.class, QqConfig.class})
public class SocialAutoConfiguration implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(SocialAutoConfiguration.class, ProjectNameConstant.TAOTAO_CLOUD_SOCIAL_STARTER, "第三方认证模块已启动");
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
