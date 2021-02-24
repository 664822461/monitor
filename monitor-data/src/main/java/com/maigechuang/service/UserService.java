package com.maigechuang.service;

import com.maigechuang.entity.User;

/**
 * Created by mgc on 2020/11/29.
 */
public interface UserService {

    /**
     * 注册
     */

    int insertUser(User user);

    /**
     * 修改
     */

    int updateUser(User user);

    /**
     * 验证
     */

    User getUser(User user);
}
