package com.taotao.cloud.common.utils;

import com.taotao.cloud.common.exception.BaseException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * BeanUtils
 *
 * @author: dengtao
 * @version: 2019-08-12 21:39
 **/
public class BeanUtil {

    public static void registerBean(String name,
                                    Class clazz,
                                    Object... args) {
        ConfigurableApplicationContext applicationContext = ContextUtil.getApplicationContext();
        checkRegisterBean(applicationContext, name, clazz);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
    }

    public static void registerBean(String name,
                                    Class clazz,
                                    BeanDefinitionBuilder beanDefinitionBuilder) {
        ConfigurableApplicationContext applicationContext = ContextUtil.getApplicationContext();
        checkRegisterBean(applicationContext, name, clazz);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);

    }

    public static void unRegisterBean(String name) {
        ConfigurableApplicationContext applicationContext = ContextUtil.getApplicationContext();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.removeBeanDefinition(name);

    }

    private static void checkRegisterBean(ApplicationContext applicationContext, String name, Class clazz) {
        if (applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return;
            } else {
                throw new BaseException("BeanName 重复注册" + name);
            }
        }
    }
}
