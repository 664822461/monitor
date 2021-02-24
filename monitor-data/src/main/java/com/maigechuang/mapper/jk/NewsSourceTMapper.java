package com.maigechuang.mapper.jk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mgc on 2020/12/9.
 */
@Mapper
public interface NewsSourceTMapper {

    void deletNews(@Param("area") String area, @Param("news") String news, @Param("url")String url);
}
