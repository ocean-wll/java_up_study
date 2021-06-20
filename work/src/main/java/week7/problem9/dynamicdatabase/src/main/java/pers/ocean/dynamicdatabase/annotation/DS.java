package pers.ocean.dynamicdatabase.annotation;

import pers.ocean.dynamicdatabase.constant.DataSourceConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源注解
 *
 * @author : ocean_wll
 * @since 2021/6/20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {

    /**
     * 数据源名称
     */
    String value() default DataSourceConstant.DATA_SOURCE_3316;
}
