//package com.taotao.cloud.shardingjdbc;
//
//import com.google.common.base.Strings;
//import com.taotao.cloud.common.config.CoreProperties;
//import com.taotao.cloud.common.util.PropertyUtils;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//
///**
// * @author: chejiangyi
// * @version: 2019-06-14 09:41
// **/
//public class ShardingJdbcApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//    @Override
//    public void initialize(ConfigurableApplicationContext context) {
//        this.initializeSystemProperty(context);
//    }
//
//    void initializeSystemProperty(ConfigurableApplicationContext context) {
//        //禁用原始 sharding-jdbc SpringBootConfiguration 启动
//        org.springframework.core.env.Environment environment = context.getEnvironment();
//
//        String propertyValue = environment.getProperty(ShardingJdbcProperties.SpringApplicationName);
//        if (Strings.isNullOrEmpty(propertyValue)) {
//           return;
//        }
//
//        setProperty(ShardingJdbcProperties.SpringShardingSphereEnabled, "false","废弃spring.shardingsphere.enabled, 请使用 bsf.shardingjdbc.enabled");
//    }
//
//    void setProperty(String key,String propertyValue,String message)
//    {
//        PropertyUtils.setDefaultInitProperty(ShardingJdbcApplicationContextInitializer.class,ShardingJdbcProperties.Project,key,propertyValue,message);
//    }
//}
