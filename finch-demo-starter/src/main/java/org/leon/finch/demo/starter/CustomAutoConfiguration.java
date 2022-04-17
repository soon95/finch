package org.leon.finch.demo.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon Song
 * @date 2022-04-17
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(CustomConfig.class)
// 如果有 TagBean 这个bean，才进行装配
@ConditionalOnBean(TagBean.class)
public class CustomAutoConfiguration {

    @Autowired
    private CustomConfig customConfig;

    @Bean
    @ConditionalOnMissingBean(CustomBean.class)
    public CustomBean customEntity() {
        log.info("CustomBean 被自动装配了");
        CustomBean customBean = new CustomBean();
        customBean.setName(customConfig.getName());
        return customBean;
    }
}
