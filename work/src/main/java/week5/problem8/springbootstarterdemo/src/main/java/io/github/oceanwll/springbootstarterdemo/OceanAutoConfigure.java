package io.github.oceanwll.springbootstarterdemo;

import io.github.oceanwll.springbootstarterdemo.properties.OceanProperties;
import io.github.oceanwll.springbootstarterdemo.service.OceanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : ocean_wll
 * @since 2021/5/30
 */
@Configuration
@EnableConfigurationProperties(OceanProperties.class)
public class OceanAutoConfigure {

    @Autowired
    private OceanProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "example.service", value = "enabled", havingValue = "true", matchIfMissing = true)
    public OceanService oceanService() {
        return new OceanService(properties.getName());
    }
}
