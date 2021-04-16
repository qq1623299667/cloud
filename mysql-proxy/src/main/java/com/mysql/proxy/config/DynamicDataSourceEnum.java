package com.mysql.proxy.config;

import lombok.Getter;

/**
 * @author 石佳
 * @since 2020/06/04/10:19
 */
@Getter
public enum DynamicDataSourceEnum {
    MASTER("master"),
    SLAVE("slave");
    private String dataSourceName;
    DynamicDataSourceEnum(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}
