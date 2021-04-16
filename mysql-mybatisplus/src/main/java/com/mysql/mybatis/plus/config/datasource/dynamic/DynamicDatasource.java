package com.mysql.mybatis.plus.config.datasource.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 石佳
 * @since 2020/07/24
 */
@Slf4j
public class DynamicDatasource extends AbstractRoutingDataSource {
    /**
     *  动态找到key，切换成对应的数据源
     * @author 石佳
     * @since 2020/7/24
     */
    @Override
    protected Object determineCurrentLookupKey() {
        Integer dataSourceId = DynamicDataSourceContextHolder.getDataSourceId();
        if (dataSourceId != null) { //有指定切换数据源切换的时候，才给输出日志 并且也只给输出成debug级别的 否则日志太多了
            log.debug("线程[{}]，此时切换到的数据源为:{}", Thread.currentThread().getId(), dataSourceId);
        }
        return dataSourceId;
    }
}
