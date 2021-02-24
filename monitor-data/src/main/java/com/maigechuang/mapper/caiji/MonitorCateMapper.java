package com.maigechuang.mapper.caiji;

import com.maigechuang.entity.MonitorCate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mgc on 2020/11/26.
 */
@Mapper
public interface MonitorCateMapper {

    int getMonitorValueByLevel(int level);

    int getCount();

   List<MonitorCate> getGradeData(@Param("limit") int limit, @Param("size") int size);

   void updateMonitorValueById(@Param("id") int id,@Param("monitorValue") int monitorValue);
}
