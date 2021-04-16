package com.mysql.mybatis.plus.config.datasource.dynamic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 石佳
 * 封装数据库连接参数
 * @since 2020/07/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBParam {
    private String host;
    private Integer port;
    private String database;
    private String username;
    private String password;

}
