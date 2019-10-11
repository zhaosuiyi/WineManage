package com.wine.common;

import com.mybaitsplus.devtools.core.config.autoconfig.EnableDevTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDevTools
@EnableCaching
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
        log.info("========================启动完毕========================");
    }
}

