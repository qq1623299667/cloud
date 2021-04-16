package com.mysql.proxy.common;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author shijia
 * @date 2019-11-20 10:12
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // FIXME 特别注意，该接口不能被扫描到，否则会出错
}
