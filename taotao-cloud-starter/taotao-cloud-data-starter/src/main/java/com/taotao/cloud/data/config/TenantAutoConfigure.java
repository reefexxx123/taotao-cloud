package com.taotao.cloud.data.config;

import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.taotao.cloud.common.constant.ProjectNameConstant;
import com.taotao.cloud.common.context.TenantContextHolder;
import com.taotao.cloud.common.utils.LogUtils;
import com.taotao.cloud.data.properties.TenantProperties;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 多租户自动配置
 *
 * @author dengtao
 * @date 2020/5/2 11:20
*/
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfigure implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LogUtils.info(TenantAutoConfigure.class, ProjectNameConstant.TAOTAO_CLOUD_TENANT_STARTER, "tenant模式已开启");
    }

    @Autowired
    private TenantProperties tenantProperties;

    @Bean
    public TenantHandler tenantHandler() {
        return new TenantHandler() {
            /**
             * 获取租户id
             */
            @Override
            public Expression getTenantId(boolean where) {
                String tenant = TenantContextHolder.getTenant();
                if (tenant != null) {
                    return new StringValue(TenantContextHolder.getTenant());
                }
                return new NullValue();
            }

            /**
             * 获取租户列名
             */
            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            /**
             * 过滤不需要根据租户隔离的表
             * @param tableName 表名
             */
            @Override
            public boolean doTableFilter(String tableName) {
                return tenantProperties.getIgnoreTables().stream().anyMatch(
                        (e) -> e.equalsIgnoreCase(tableName)
                );
            }
        };
    }

    /**
     * 过滤不需要根据租户隔离的MappedStatement
     */
    @Bean
    public ISqlParserFilter sqlParserFilter() {
        return metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            return tenantProperties.getIgnoreSqls().stream().anyMatch(
                    (e) -> e.equalsIgnoreCase(ms.getId())
            );
        };
    }
}
