package com.wine.common.config;

import com.mybaitsplus.devtools.core.datasource.framework.strategy.StrategyManager;
import com.mybaitsplus.devtools.core.datasource.framework.strategy.impl.YYYYMMStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分表策略管理类
 */
@Configuration
public class StrategyConfig {

    /**
     * 策略配置类
     * @return
     */
    @Bean
    public StrategyManager strategyManager() {
        StrategyManager strategyManager = new StrategyManager();
        strategyManager.addStrategy(StrategyManager._YYYYMM, new YYYYMMStrategy());
        return strategyManager;
    }

}