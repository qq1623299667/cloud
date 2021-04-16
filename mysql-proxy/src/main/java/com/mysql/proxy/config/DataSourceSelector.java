package com.mysql.proxy.config;

import java.lang.annotation.*;

/**
 * @author 石佳
 * @since 2020/06/04/10:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {
    DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;
    boolean clear() default true;
}
