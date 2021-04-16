package com.mysql.mybatis.plus.service.impl;

import com.mysql.mybatis.plus.config.datasource.dynamic.DynamicDataSourceContextHolder;
import com.mysql.mybatis.plus.model.User;
import com.mysql.mybatis.plus.mapper.UserMapper;
import com.mysql.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 石佳
 * @since 2020-07-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Long createUser(User user) {
        DynamicDataSourceContextHolder.setDataSourceId(1);
        userMapper.insert(user);

        // 切换到其他两个库，进行同步设置,此时id也保证一样
        DynamicDataSourceContextHolder.setDataSourceId(2);
        userMapper.insert(user);
        DynamicDataSourceContextHolder.setDataSourceId(3);
        userMapper.insert(user);
        return user.getId();
    }
}
