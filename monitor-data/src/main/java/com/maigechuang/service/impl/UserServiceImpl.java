package com.maigechuang.service.impl;

import com.maigechuang.entity.User;
import com.maigechuang.mapper.caiji.UserMapper;
import com.maigechuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mgc on 2020/11/29.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }
}
