package com.taotao.cloud.common.initializer;

import com.taotao.cloud.common.utils.ContextUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * CoreApplicationContextInitializer
 *
 * @author dengtao
 * @date 2020/5/15 10:45
 */
public class CoreApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        if (ContextUtil.MainClass == null) {
            ContextUtil.MainClass = deduceMainApplicationClass();
        }
        ContextUtil.setApplicationContext(context);
    }

    private Class<?> deduceMainApplicationClass() {
        try {
            StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                if ("main".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        } catch (ClassNotFoundException ex) {
            // Swallow and continue
        }
        return null;
    }
}
