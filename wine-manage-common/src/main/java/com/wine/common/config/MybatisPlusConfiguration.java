package com.wine.common.config;


import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.mybaitsplus.devtools.core.datasource.framework.interceptor.TableSplitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages={MybatisPlusConfiguration.MAPPER_PACKAGES},sqlSessionFactoryRef="sqlSessionFactory")
public class MybatisPlusConfiguration {
    /**
     * mapper 所在包
     */
    public static final String MAPPER_PACKAGES = "com.wine.**.mapper";
    /**
     * MAPPER_LOCATIONS
     */
    public static final String MAPPER_LOCATIONS= "classpath*:mappers/**/*.xml";
    /**
     * 实体类 所在包
     */
    public static final String ENTITY_PACKAGES = "com.wine.**.model";
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * 水平分表
     */
    @Bean
    public TableSplitInterceptor tableSplitInterceptor() {
        return new TableSplitInterceptor();
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MybatisPlusConfiguration.MAPPER_LOCATIONS));
        sqlSessionFactory.setTypeAliasesPackage(MybatisPlusConfiguration.ENTITY_PACKAGES);
        sqlSessionFactory.setGlobalConfig(globalConfiguration());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor() //添加分页功能
                ,tableSplitInterceptor()
        });
        return sqlSessionFactory.getObject();
    }

    /**
     * 配置@Transactional注解事物
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setIdType(0);
        return conf;
    }
}

