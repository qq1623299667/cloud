package com.mysql.mybatis.plus.config.datasource.dynamic;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 石佳
 * @since 2020/07/24
 */
@Component
public class DynamicDatasourceConfig {
    // 这个地方可以初始化，为了展示，直接硬编码在代码中
    private Map<Object, Object> dataSourceMap = new HashMap();

    // 注册的时候，将一堆数据源以map的形式注册到容器。
    // 使用的时候，从AbstractRoutingDataSource.determineCurrentLookupKey()方法取得key，然后通过DataSource dataSource = this.resolvedDataSources.get(lookupKey);拿到数据源
    @Primary    //当一个接口有两个实现类时，并两个实现类都被 Spring 管理，则需要对某个类进行 @Primary 注解，表示优先选择此实现类。
    @Bean
    public DynamicDatasource dataSource() {
        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        dataSourceMap.put(1,getDataSource(new DBParam("localhost",3306,"test1","root","root")));
        dataSourceMap.put(2,getDataSource(new DBParam("localhost",3306,"test2","root","root")));
        dataSourceMap.put(3,getDataSource(new DBParam("localhost",3306,"test3","root","root")));
        dynamicDatasource.setTargetDataSources(dataSourceMap);
        dynamicDatasource.setDefaultTargetDataSource(dataSourceMap.get(1));
        return dynamicDatasource;
    }

    /**
    *  将参数设置成对应的连接池的设置并且设置DataSource
    * @author 石佳
    * @since 2020/7/24
    */
    private static DataSource getDataSource(DBParam dbParam) {
        HikariConfig config = new HikariConfig();
        config.setUsername(dbParam.getUsername());
        config.setPassword(dbParam.getPassword());
        config.setJdbcUrl(makeMySqlUrl(dbParam));
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(100);
        config.setMaxLifetime(3600000L);
        HikariDataSource hikariDataSource = new HikariDataSource(config);
        return (DataSource)hikariDataSource;
    }

    private static String makeMySqlUrl(DBParam dbParam) {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://");
        sb.append(dbParam.getHost());
        sb.append(":");
        sb.append(dbParam.getPort());
        sb.append("/");
        sb.append(dbParam.getDatabase());
        sb.append("?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true&failOverReadOnly=false&useSSL=false");
        return sb.toString();
    }
}
