package com.wine.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mybaitsplus.devtools.core.datasource.database.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 */
@Configuration
public class DataSourceConfig  {

    public static final String DATA_SOURCE_DEFAULE_DS = "default_ds";
    public static final String DATA_SOURCE_QUARTZ = "quartz";


    @Bean(DATA_SOURCE_DEFAULE_DS)
    @ConfigurationProperties(prefix = "spring.datasource.default" )
    public DataSource ds() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("default_ds")
    public DataSource dataSource() {
        Map< Object, Object > targetDataSources = new HashMap<>();

        targetDataSources.put(DATA_SOURCE_DEFAULE_DS, ds());

        return new DynamicDataSource(ds(),targetDataSources);
    }



}
