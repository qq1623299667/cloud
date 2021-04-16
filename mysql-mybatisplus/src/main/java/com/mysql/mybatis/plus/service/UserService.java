package com.mysql.mybatis.plus.service;

import com.mysql.mybatis.plus.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 石佳
 * @since 2020-07-24
 */
public interface UserService extends IService<User> {

    Long createUser(User user);
}
