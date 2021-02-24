package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by mgc on 2020/11/29.
 */


@Mapper
public interface UserMapper {

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
