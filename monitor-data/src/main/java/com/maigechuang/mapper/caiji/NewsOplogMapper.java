package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.NewsOplog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 66482 on 2020/6/2.
 */

@Mapper
public interface NewsOplogMapper {



    List<NewsOplog> getNewsLogList();

    int insertlog(NewsOplog newsOplog);


}
