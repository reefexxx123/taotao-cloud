package com.taotao.cloud.shardingjdbc;


import java.lang.annotation.Annotation;

import com.taotao.cloud.common.exception.BaseException;
import com.taotao.cloud.shardingjdbc.base.DataSource;
import com.taotao.cloud.shardingjdbc.base.SlaveOnly;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author: chejiangyi
 * @version: 2019-09-01 14:25
 **/
@Aspect
public class ShardingJdbcDynamicDataSourceAspect {
    @Pointcut("@within(com.taotao.cloud.shardingjdbc.base.MasterOnly) " +
            "|| @annotation(com.taotao.cloud.shardingjdbc.base.MasterOnly)")
    public void masterOnly() {

    }

    @Around("masterOnly()")
    public Object handleMasterOnly(ProceedingJoinPoint joinPoint) throws Throwable {
       return ShardingJdbcUtils.hitMasterOnly(()-> {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new BaseException(e);
                }
            }
        );
    }

    @Pointcut("@within(com.taotao.cloud.shardingjdbc.base.SlaveOnly) " +
            "|| @annotation(com.taotao.cloud.shardingjdbc.base.SlaveOnly)")
    public void slaveOnly() {

    }

    @Around("slaveOnly()")
    public Object handleSlaveOnly(ProceedingJoinPoint joinPoint) throws Throwable {
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    	Annotation  annotation = methodSignature.getMethod().getAnnotation(SlaveOnly.class);
    	if(annotation==null) {
    		annotation=joinPoint.getSignature().getDeclaringType().getAnnotation(SlaveOnly.class);
    	}   	
        return ShardingJdbcUtils.hitSlaveOnly(((SlaveOnly)annotation).slave(),()-> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new BaseException(e);
                    }
                }
        );
    }

    @Pointcut("@within(com.taotao.cloud.shardingjdbc.base.DataSource) " +
            "|| @annotation(com.taotao.cloud.shardingjdbc.base.DataSource)")
    public void dataSource() {

    }

    @SuppressWarnings("unchecked")
	@Around("dataSource()")
    public Object handleDataSource(ProceedingJoinPoint joinPoint) throws Throwable {    	
    	MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    	Annotation  annotation = methodSignature.getMethod().getAnnotation(DataSource.class);
    	if(annotation==null) {
    		annotation=joinPoint.getSignature().getDeclaringType().getAnnotation(DataSource.class);
    	}
        return ShardingJdbcUtils.hitDataSource(((DataSource)annotation).name(),()-> {
                    try {
                        return joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new BaseException(e.getMessage(), e);
                    }
                }
        );
    }
}
