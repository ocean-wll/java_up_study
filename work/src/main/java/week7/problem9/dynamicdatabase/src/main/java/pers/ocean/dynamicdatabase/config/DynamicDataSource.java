package pers.ocean.dynamicdatabase.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import pers.ocean.dynamicdatabase.context.DynamicDataSourceContextHolder;

/**
 * @author : ocean_wll
 * @since 2021/6/20
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getContextKey();
    }
}
