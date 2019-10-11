package com.wine.web;

import com.mybaitsplus.devtools.core.config.autoconfig.EnableDevTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import com.wine.common.CommonApplication;

@Slf4j
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CommonApplication.class}))
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDevTools
@EnableCaching
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.info("========================启动完毕========================");
    }
}

