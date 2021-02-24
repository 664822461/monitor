package com.maigechuang.mapper.jk;

import com.maigechuang.entity.SendMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SendMessageMapper {

    List<SendMessage> getMessageList(@Param("limit") int limit, @Param("size")int size);

    int getCount();


}
