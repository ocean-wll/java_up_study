package week5.problem2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring注入对象的方式：
 * 1、xml配置
 * 2、@Bean
 * 3、通过 BeanFactory动态注入对象
 *
 * @author : ocean_wll
 * @since 2021/5/29
 */
@Configuration
public class OceanConfiguration {

    /**
     * 通过 @Bean 注入对象
     *
     * @return Ocean
     */
    @Bean
    public Ocean ocean() {
        return new Ocean();
    }
}
