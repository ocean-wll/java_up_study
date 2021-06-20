package pers.ocean.dynamicdatabase.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import pers.ocean.dynamicdatabase.constant.DataSourceConstant;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @author : ocean_wll
 * @since 2021/6/20
 */
@Configuration
public class DynamicDataSourceConfiguration {


    @Bean(DataSourceConstant.DATA_SOURCE_3316)
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DataSourceConstant.DATA_SOURCE_3326)
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(@Qualifier(DataSourceConstant.DATA_SOURCE_3316) DataSource dataSource3316,
                                        @Qualifier(DataSourceConstant.DATA_SOURCE_3326) DataSource dataSource3326) {
        Map<Object, Object> dataSourceMap = new HashMap<>(8);
        dataSourceMap.put(DataSourceConstant.DATA_SOURCE_3316, dataSource3316);
        dataSourceMap.put(DataSourceConstant.DATA_SOURCE_3326, dataSource3326);
        //设置动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSource3316);
        return dynamicDataSource;
    }

    @Bean(name = "txManager3316")
    @Primary
    public PlatformTransactionManager txManager3316(@Qualifier(DataSourceConstant.DATA_SOURCE_3316) DataSource dataSource3316) {
        return new DataSourceTransactionManager(dataSource3316);
    }

    @Bean(name = "txManager3326")
    @Primary
    public PlatformTransactionManager txManager3326(@Qualifier(DataSourceConstant.DATA_SOURCE_3326) DataSource dataSource3326) {
        return new DataSourceTransactionManager(dataSource3326);
    }
}
