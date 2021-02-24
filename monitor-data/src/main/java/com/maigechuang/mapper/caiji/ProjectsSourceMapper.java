package com.maigechuang.mapper.caiji;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectsSourceMapper {

    int findCountByName(@Param("name") String name, @Param("startTime") String startTime,@Param("endTime") String endTime);

    int findyesData(@Param("startTime") String startTime,@Param("endTime") String endTime);
    int beforeyesData(@Param("startTime") String startTime,@Param("endTime") String endTime);

    int findeyesfvData(@Param("startTime") String startTime,@Param("endTime") String endTime);
    int beforeyesefvData(@Param("startTime") String startTime,@Param("endTime") String endTime);


}
